package com.fiveis.practicecamera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fiveis.practicecamera.presentation.theme.PracticeCameraTheme
import com.fiveis.practicecamera.presentation.ui.qr.QrScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeCameraTheme {
                QrScreen()
            }
        }
    }
}