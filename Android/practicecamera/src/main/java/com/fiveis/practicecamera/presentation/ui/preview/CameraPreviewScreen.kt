package com.fiveis.practicecamera.presentation.ui.preview

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.fiveis.practicecamera.presentation.ui.qr.QrViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.concurrent.Executors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraPreviewScreen(
    onBack: () -> Unit,
    viewModel: QrViewModel = viewModel()
) {
    val context = LocalContext.current
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("QR 스캔") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Text(text = "X")
                    }
                }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { ctx ->
                        val previewView = PreviewView(ctx)
                        startCamera(ctx, previewView, viewModel, cameraExecutor, onBack)
                        previewView
                    }
                )
            }
        }
    )
}

@SuppressLint("UnsafeOptInUsageError")
private fun startCamera(
    context: Context,
    previewView: PreviewView,
    viewModel: QrViewModel,
    cameraExecutor: java.util.concurrent.ExecutorService,
    onBack: () -> Unit
) {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    cameraProviderFuture.addListener({
        val cameraProvider = cameraProviderFuture.get()

        val preview = Preview.Builder().build().also {
            it.setSurfaceProvider(previewView.surfaceProvider)
        }

        val imageAnalysis = ImageAnalysis.Builder().build().also { analysis ->
            analysis.setAnalyzer(cameraExecutor, QRCodeAnalyzer { qrText ->
                if (qrText.isNotEmpty()) {
                    viewModel.updateQr(qrText)
                    onBack()  // 스캔 성공 시 이전 화면으로 전환
                }
            })
        }

        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                context as androidx.lifecycle.LifecycleOwner,
                cameraSelector,
                preview,
                imageAnalysis
            )
        } catch (exc: Exception) {
            Log.e("CameraPreview", "카메라 바인딩 실패", exc)
        }
    }, ContextCompat.getMainExecutor(context))
}