package com.fiveis.practicemvvm.presentation.ui.common

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fiveis.practicemvvm.presentation.ui.input.InputScreen
import com.fiveis.practicemvvm.presentation.ui.home.HomeScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier

@Composable
fun NavBar() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar {
                Button(onClick = { navController.navigate("home") }) {
                    Text("Home")
                }
                Button(onClick = { navController.navigate("input") }) {
                    Text("Input")
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") { HomeScreen() }
            composable("input") { InputScreen() }
        }
    }
}