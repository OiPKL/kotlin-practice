package com.fiveis.practicecamera.domain.repository

import com.fiveis.practicecamera.data.dto.QrDto

class QrRepositoryImpl : QrRepository {
    private var qrData: QrDto? = null

    override fun saveQr(qrDto: QrDto) {
        qrData = qrDto
    }

    override fun getQr(): QrDto? = qrData
}