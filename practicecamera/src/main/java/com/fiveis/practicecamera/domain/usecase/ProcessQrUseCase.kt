package com.fiveis.practicecamera.domain.usecase

import com.fiveis.practicecamera.data.dto.QrDto
import com.fiveis.practicecamera.domain.repository.QrRepository

class ProcessQrUseCase(private val repository: QrRepository) {
    fun process(qrString: String) {
        val dto = QrDto(qrString)
        repository.saveQr(dto)
    }
}