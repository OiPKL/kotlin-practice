package com.fiveis.practiceapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fiveis.practiceapi.presentation.theme.MyAppTheme
import com.fiveis.practiceapi.presentation.ui.json.TodoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                TodoScreen()
            }
        }
    }
}