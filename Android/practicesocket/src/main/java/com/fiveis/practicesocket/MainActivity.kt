package com.fiveis.practicesocket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fiveis.practicesocket.presentation.ui.input.SocketInputScreen
import com.fiveis.practicesocket.presentation.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                SocketInputScreen()
            }
        }
    }
}
