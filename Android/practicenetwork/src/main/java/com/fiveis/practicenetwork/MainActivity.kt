package com.fiveis.practicenetwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fiveis.practicenetwork.presentation.ui.network.NetworkScreen
import com.fiveis.practicenetwork.presentation.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                NetworkScreen()
            }
        }
    }
}