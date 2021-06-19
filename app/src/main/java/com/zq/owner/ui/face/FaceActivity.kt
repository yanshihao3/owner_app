package com.zq.owner.ui.face


import android.content.Intent
import android.net.Uri
import android.util.Log
import android.util.Size
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import com.zq.base.activity.BaseNoModelActivity
import com.zq.base.utils.SharedPreferencesUtils
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityFaceBinding
import com.zq.owner.utils.FileUtils
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FaceActivity : BaseNoModelActivity<AppActivityFaceBinding>() {

    override val layoutId: Int
        get() = R.layout.app_activity_face

    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>

    private val imageCapture = ImageCapture.Builder()
        .build()
    private lateinit var cameraExecutor: ExecutorService

    override fun initView() {
        mDataBind.toolbar.title.text = "人脸认证"
        mDataBind.toolbar.backIv.setOnClickListener {
            finish()
        }
        cameraExecutor = Executors.newSingleThreadExecutor()
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider)
        }, ContextCompat.getMainExecutor(this))
    }

    private var isFirst = true

    private fun bindPreview(cameraProvider: ProcessCameraProvider) {
        val preview: Preview = Preview.Builder()
            .build()


        val cameraSelector: CameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
            .build()
        preview.setSurfaceProvider(mDataBind.previewView.surfaceProvider)
        val imageAnalysis = ImageAnalysis.Builder()
            .setTargetResolution(Size(1280, 720))
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()

        imageAnalysis.setAnalyzer(cameraExecutor, ImageAnalysis.Analyzer { image ->
            image.close()
            if (isFirst) {
                isFirst = false
                mDataBind.faceView.post {
                    mDataBind.faceView.start()
                }
            }

        })

        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture, imageAnalysis)
    }

    override fun initData() {
        val outputDirectory = FileUtils.getCacheDir(this)
        mDataBind.btn.setOnClickListener {
            val photoFile = createFile(outputDirectory, FILENAME, PHOTO_EXTENSION)
            val outputFileOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

            imageCapture.takePicture(outputFileOptions, cameraExecutor,
                object : ImageCapture.OnImageSavedCallback {
                    override fun onError(error: ImageCaptureException) {
                        Log.e("TAG", "Photo capture failed: ${error.message}", error)
                    }

                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        val savedUri = outputFileResults.savedUri ?: Uri.fromFile(photoFile)
                        Log.d("TAG", "Photo capture succeeded: $savedUri")
                        SharedPreferencesUtils.init(mActivityContext)
                            .putString("face", savedUri.toString())
                        startActivity(Intent(mActivityContext, FaceSuccessActivity::class.java))
                        finish()
                    }
                })

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDataBind.faceView.destroyView()
    }


    companion object {
        private const val FILENAME = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val PHOTO_EXTENSION = ".jpg"


        private fun createFile(baseFolder: String, format: String, extension: String) =
            File(
                baseFolder, SimpleDateFormat(format, Locale.CHINA)
                    .format(System.currentTimeMillis()) + extension
            )
    }
}