package com.fiveis.practicecamera.presentation.ui.qr

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fiveis.practicecamera.domain.repository.QrRepositoryImpl
import com.fiveis.practicecamera.domain.usecase.ProcessQrUseCase

class QrViewModel : ViewModel() {
    // 스캔 결과 문자열 상태
    val qrResult = mutableStateOf("")

    // Repository와 UseCase 초기화
    private val repository = QrRepositoryImpl()
    private val processQrUseCase = ProcessQrUseCase(repository)

    fun updateQr(qrString: String) {
        processQrUseCase.process(qrString)
        qrResult.value = qrString
    }
}