package com.bangkit.ewaste.ui.post

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bangkit.ewaste.databinding.ActivityPostWasteBinding
import com.bangkit.ewaste.ui.form.FormActivity
import com.bangkit.ewaste.utils.ConstVal.KEY_PICTURE
import com.bangkit.ewaste.utils.ConstVal.KEY_SELECTED_IMAGE_URI
import com.bangkit.ewaste.utils.EcoViewModelFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@Suppress("DEPRECATION")
class PostWasteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPostWasteBinding
    private lateinit var viewModel : PostWasteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostWasteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()

        val intent = intent

        val pictureFile = intent.getSerializableExtra(KEY_PICTURE) as? File
        val imageUriString = intent.getStringExtra(KEY_SELECTED_IMAGE_URI)

        viewModel.ewasteId.observe(this@PostWasteActivity){
            viewModel.getElektronnikById(it)
            viewModel.elektronikItem.observe(this@PostWasteActivity){ item->
                binding.tvTitle.text = item.jenisElektronik
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
            FormActivity.start(this, "auto")
            finish()
        }

    }

    private fun setupViewModel() {
        val viewModelFactory = EcoViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[PostWasteViewModel::class.java]
    }


}