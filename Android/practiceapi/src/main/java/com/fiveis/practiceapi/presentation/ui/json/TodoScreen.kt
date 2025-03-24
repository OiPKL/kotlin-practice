package com.fiveis.practiceapi.presentation.ui.json

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fiveis.practiceapi.presentation.ui.json.TodoViewModel

@Composable
fun TodoScreen(viewModel: TodoViewModel = viewModel()) {
    val uiState = viewModel.uiState

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // GET, POST, Local 버튼 영역
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { viewModel.onGetTodo() }) {
                Text("GET")
            }
            Button(onClick = { viewModel.onPostTodo() }) {
                Text("POST")
            }
            Button(onClick = { viewModel.onLocalTodo() }) {
                Text("Local")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // POST 요청을 위한 입력 필드들
        OutlinedTextField(
            value = viewModel.userIdInput,
            onValueChange = { viewModel.userIdInput = it },
            label = { Text("User ID") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.titleInput,
            onValueChange = { viewModel.titleInput = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Completed", style = MaterialTheme.typography.bodyMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = viewModel.completedInput == "true",
                        onClick = { viewModel.completedInput = "true" }
                    )
                    Text(text = "True")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = viewModel.completedInput == "false",
                        onClick = { viewModel.completedInput = "false" }
                    )
                    Text(text = "False")
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // 결과 출력 영역
        Text(
            text = uiState.value.resultText,
//            text = "Result: ${uiState.value.resultText}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}