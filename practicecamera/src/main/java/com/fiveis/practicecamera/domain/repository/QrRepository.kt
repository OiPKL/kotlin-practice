package com.fiveis.practicecamera.domain.repository

import com.fiveis.practicecamera.data.dto.QrDto

interface QrRepository {
    fun saveQr(qrDto: QrDto)
    fun getQr(): QrDto?
}