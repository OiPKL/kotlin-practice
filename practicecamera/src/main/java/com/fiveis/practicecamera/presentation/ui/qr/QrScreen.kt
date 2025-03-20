package com.fiveis.practicecamera.presentation.ui.qr

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QrScreen(
    onScanClick: () -> Unit,
    viewModel: QrViewModel = viewModel()
) {
    val context = LocalContext.current
    var errorMessage by remember { mutableStateOf("") }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = RequestPermission()
    ) { granted ->
        if (granted) {
            errorMessage = ""
            onScanClick()
        } else {
            errorMessage = "카메라 권한이 필요합니다."
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("QR 코드 스캔") })
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    // 카메라 권한 확인
                    val hasPermission = ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED

                    if (hasPermission) {
                        onScanClick()
                    } else {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }) {
                    Text("스캔 시작")
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (viewModel.qrResult.value.isNotEmpty()) {
                    Text("스캔 결과: ${viewModel.qrResult.value}")
                }
                if (errorMessage.isNotEmpty()) {
                    Text("에러: $errorMessage", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    )
}