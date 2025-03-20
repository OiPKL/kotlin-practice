package com.fiveis.practicecamera

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.os.Bundle
import androidx.compose.runtime.*
import com.fiveis.practicecamera.presentation.theme.PracticeCameraTheme
import com.fiveis.practicecamera.presentation.ui.preview.CameraPreviewScreen
import com.fiveis.practicecamera.presentation.ui.qr.QrScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeCameraTheme {
                // 간단한 상태로 화면 전환 (스캔 화면과 카메라 프리뷰 화면)
                var showCameraPreview by remember { mutableStateOf(false) }
                if (showCameraPreview) {
                    CameraPreviewScreen(
                        onBack = { showCameraPreview = false }
                    )
                } else {
                    QrScreen(
                        onScanClick = { showCameraPreview = true }
                    )
                }
            }
        }
    }
}