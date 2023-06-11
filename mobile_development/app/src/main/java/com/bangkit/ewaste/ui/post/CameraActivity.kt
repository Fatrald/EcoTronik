package com.bangkit.ewaste.ui.post

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bangkit.ewaste.databinding.ActivityCameraBinding
import com.bangkit.ewaste.utils.ConstVal.CAMERA_X_RESULT
import com.bangkit.ewaste.utils.ConstVal.KEY_IS_BACK_CAMERA
import com.bangkit.ewaste.utils.ConstVal.KEY_PICTURE
import com.bangkit.ewaste.utils.ConstVal.REQUEST_CODE_PERMISSIONS
import com.bangkit.ewaste.utils.createFile
import com.bangkit.ewaste.utils.showToast
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {

    private var _activityCameraBinding : ActivityCameraBinding? = null
    private val binding get() = _activityCameraBinding!!

    private lateinit var cameraExecutor : ExecutorService
    private var cameraSelector : CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private var imageCapture : ImageCapture? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityCameraBinding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(_activityCameraBinding?.root)

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        initExecutor()
        initAction()
    }

    override fun onResume() {
        super.onResume()
        startCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun initAction() {
        binding.apply {
            captureImage.setOnClickListener {
                takePhoto()
            }
        }
    }

    private fun initExecutor() {
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return
        val photoFile = createFile(application)

        val outputOption = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOption,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val intent = Intent(this@CameraActivity, PostWasteActivity::class.java)
                    intent.putExtra(KEY_PICTURE, photoFile)
                    intent.putExtra(KEY_IS_BACK_CAMERA, cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA)
                    setResult(CAMERA_X_RESULT, intent)
                    startActivity(intent)
                    finish()
                }

                override fun onError(exception: ImageCaptureException) {
                    showToast("Upload Failed")
                    Log.d(TAG, exception.toString())
                }
            }
        )
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }
            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (ex: Exception) {
                Toast.makeText(this, "Gagal memunculkan kamera", Toast.LENGTH_SHORT).show()
                Log.d(TAG, ex.toString())
            }
        }, ContextCompat.getMainExecutor(this))
    }

    companion object {
        const val TAG = "CameraActivity"

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

    }


}