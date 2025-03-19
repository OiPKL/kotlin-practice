package com.fiveis.practicecamera.presentation.ui.qr

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.view.ViewGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.fiveis.practicecamera.presentation.theme.PracticeCameraTheme
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Composable
fun QrScreen() {
    val viewModel = remember { QrViewModel() }
    val qrData by viewModel.qrData.collectAsState()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCameraPermission = isGranted
    }

    val cameraExecutor: ExecutorService = remember { Executors.newSingleThreadExecutor() }
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    var previewView by remember { mutableStateOf<PreviewView?>(null) }

    PracticeCameraTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (!hasCameraPermission) {
                Button(
                    onClick = { cameraPermissionLauncher.launch(Manifest.permission.CAMERA) }
                ) {
                    Text("Request Camera Permission")
                }
            } else {
                AndroidView(
                    factory = { ctx ->
                        PreviewView(ctx).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            scaleType = PreviewView.ScaleType.FILL_CENTER
                            previewView = this
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )

                LaunchedEffect(Unit) {
                    cameraProviderFuture.addListener({
                        val cameraProvider = cameraProviderFuture.get()
                        val preview = androidx.camera.core.Preview.Builder().build().also {
                            it.setSurfaceProvider(previewView?.surfaceProvider)
                        }
                        val cameraSelector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()
                        try {
                            cameraProvider.unbindAll()
                            val camera: Camera = cameraProvider.bindToLifecycle(
                                lifecycleOwner, cameraSelector, preview
                            )
                        } catch (exc: Exception) {
                            Log.e("CameraX", "Use case binding failed", exc)
                        }
                    }, ContextCompat.getMainExecutor(context))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            qrData?.let {
                Text(text = "Scanned QR: $it")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQrScreen() {
    PracticeCameraTheme {
        QrScreen()
    }
}