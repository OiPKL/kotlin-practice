package com.fiveis.practicecamera.domain.usecase

import com.fiveis.practicecamera.domain.repository.QrRepository

class ProcessQrUseCase(
    private val repository: QrRepository
) {
    fun execute(data: String) {
        repository.saveQrData(data)
    }
}