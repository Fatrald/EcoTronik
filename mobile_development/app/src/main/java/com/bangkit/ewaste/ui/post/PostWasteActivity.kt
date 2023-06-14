package com.bangkit.ewaste.ui.post

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bangkit.ewaste.MainActivity
import com.bangkit.ewaste.R
import com.bangkit.ewaste.databinding.ActivityPostWasteBinding
import com.bangkit.ewaste.ui.form.FormActivity
import com.bangkit.ewaste.ui.form.FormResultActivity
import com.bangkit.ewaste.utils.ConstVal.KEY_PICTURE
import com.bangkit.ewaste.utils.ConstVal.KEY_SELECTED_IMAGE_URI
import com.bangkit.ewaste.utils.EcoViewModelFactory
import com.bangkit.ewaste.utils.rotateFile
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.StorageOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

@Suppress("DEPRECATION")
class PostWasteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPostWasteBinding
    private lateinit var viewModel : PostWasteViewModel
    private lateinit var fullpath : String
    private lateinit var eWastePoint : String

    @SuppressLint("StringFormatMatches")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostWasteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()

        val intent = intent

        val pictureFile = intent.getSerializableExtra(KEY_PICTURE) as? File
        pictureFile?.let { rotateFile(it) }
        val imageUriString = intent.getStringExtra(KEY_SELECTED_IMAGE_URI)

        viewModel.ewasteId.observe(this@PostWasteActivity){
            viewModel.getElektronnikById(it)
            viewModel.elektronikItem.observe(this@PostWasteActivity){ item->
                binding.status.visibility = View.VISIBLE
                binding.tvTitle.text = item.jenisElektronik
                val wasteType = item.jenisElektronik
                val EP = item.point
                eWastePoint = EP.toString()
                binding.tvDescription.text = getString(R.string.scan_description, wasteType, EP)
            }
        }

        if (pictureFile != null) {
            val bitmap = BitmapFactory.decodeFile(pictureFile.path)
            val requestImageFile = pictureFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "image",
                pictureFile.name,
                requestImageFile
            )
            viewModel.predictImage(imageMultipart)

            binding.imgPreview.setImageBitmap(bitmap)
        }

        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            binding.imgPreview.setImageURI(imageUri)
        }

        binding.btnSuccess.setOnClickListener {
            val bucketUrl = "https://storage.googleapis.com/"
            val bucketName = "eco-tronik"
            val folderName = "ewaste"
            val photoFileName = pictureFile?.name
            val photoFilePath = pictureFile?.absolutePath
            val backgroundScope = CoroutineScope(Dispatchers.IO)
            backgroundScope.launch {
                //            UPLOAD GCP BUCKET
                // Set up Google Cloud Storage client
                val stream : InputStream = resources.openRawResource(R.raw.capstone)
                val credential = GoogleCredentials.fromStream(stream)
                val storage = StorageOptions.newBuilder().setCredentials(credential).build().service
//             Create a blob ID for the photo file
                val blobName = "$folderName/$photoFileName"
                val blobInfo = BlobInfo.newBuilder(bucketName, blobName).build()
                // Upload the photo file to the bucket
//                storage.create(blobInfo, FileInputStream(photoFilePath))
                // Launch the upload operation as a child coroutine
                val uploadJob = launch {
                    storage.create(blobInfo, FileInputStream(photoFilePath))
                    fullpath = "$bucketUrl$bucketName/ecotronik_$blobName"
                }
                // Wait for all child coroutines to complete
                uploadJob.join()
                // Continue executing the remaining code in the background
                // Continue executing the remaining code in the background
                withContext(Dispatchers.Main) {
                    val id = viewModel.ewasteId.value
                    if (id != null) {
                        val uuid = viewModel.getUUID()
                        viewModel.createTransaksiByImage(uuid, id, fullpath)
                    }

                    // Finish the activity
                    finish()
                }

            }
            val resultIntent = Intent(this, FormResultActivity::class.java)
            intent.putExtra("wasteCount", "1")
            intent.putExtra("wastePoint", eWastePoint)
            startActivity(resultIntent)
        }



    }

    private fun setupViewModel() {
        val viewModelFactory = EcoViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[PostWasteViewModel::class.java]
    }


}