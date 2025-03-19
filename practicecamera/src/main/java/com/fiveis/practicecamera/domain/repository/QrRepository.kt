package com.fiveis.practicecamera.domain.repository

import com.fiveis.practicecamera.data.dto.QrDto

interface QrRepository {
    fun saveQrData(data: String)
    fun getQrData(): QrDto?
}