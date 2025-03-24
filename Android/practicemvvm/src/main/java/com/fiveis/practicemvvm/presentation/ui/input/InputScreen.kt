package com.fiveis.practicemvvm.presentation.ui.input

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InputScreen(viewModel: InputViewModel = viewModel()) {
    var text by remember { mutableStateOf("") }
    val savedText by viewModel.inputText.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter Text") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.saveInput(text) }) {
            Text("Save")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Saved: $savedText")
    }
}