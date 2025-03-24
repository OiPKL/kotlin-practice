package com.fiveis.practicesocket.presentation.ui.input

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SocketInputScreen(viewModel: SocketInputViewModel = viewModel()) {
    val connectionStatus by viewModel.connectionStatus.collectAsState()
    val receivedMessage by viewModel.receivedMessage.collectAsState()

    var jwtInput by remember { mutableStateOf("") }
    var cmdInput by remember { mutableStateOf("") }
    var senderInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "연결 상태: $connectionStatus",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { viewModel.connectSocket() }) {
                Text("소켓 연결")
            }
            Button(onClick = { viewModel.disconnectSocket() }) {
                Text("소켓 해제")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = jwtInput,
            onValueChange = { jwtInput = it },
            label = { Text("JWT 입력") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = cmdInput,
            onValueChange = { cmdInput = it },
            label = { Text("CMD 입력") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = senderInput,
            onValueChange = { senderInput = it },
            label = { Text("Sender 입력") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.sendMessage(jwtInput, cmdInput, senderInput) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("메시지 전송")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "수신 메시지:",
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = receivedMessage,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}