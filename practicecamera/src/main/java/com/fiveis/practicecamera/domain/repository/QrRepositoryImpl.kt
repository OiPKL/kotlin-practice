package com.fiveis.practicecamera.domain.repository

import com.fiveis.practicecamera.data.dto.QrDto

class QrRepositoryImpl : QrRepository {
    private var qrDto: QrDto? = null

    override fun saveQrData(data: String) {
        qrDto = QrDto(data)
    }

    override fun getQrData(): QrDto? {
        return qrDto
    }
}