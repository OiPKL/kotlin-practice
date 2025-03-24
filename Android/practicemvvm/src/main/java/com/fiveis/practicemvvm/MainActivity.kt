package com.fiveis.practicemvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fiveis.practicemvvm.presentation.ui.common.NavBar
import com.fiveis.practicemvvm.presentation.theme.PracticeMVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeMVVMTheme {
                NavBar()
            }
        }
    }
}