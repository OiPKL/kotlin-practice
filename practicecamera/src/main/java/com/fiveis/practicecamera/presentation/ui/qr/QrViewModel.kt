package com.fiveis.practicecamera.presentation.ui.qr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiveis.practicecamera.domain.usecase.ProcessQrUseCase
import com.fiveis.practicecamera.domain.repository.QrRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QrViewModel : ViewModel() {
    private val repository = QrRepositoryImpl()
    private val processQrUseCase = ProcessQrUseCase(repository)

    private val _qrData = MutableStateFlow<String?>(null)
    val qrData: StateFlow<String?> get() = _qrData

    fun processQrCode(data: String) {
        viewModelScope.launch {
            processQrUseCase.execute(data)
            _qrData.value = data
        }
    }
}