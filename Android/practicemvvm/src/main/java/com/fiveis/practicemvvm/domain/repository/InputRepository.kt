package com.fiveis.practicemvvm.domain.repository

import com.fiveis.practicemvvm.data.dto.InputDto

interface InputRepository {
    fun saveInput(input: InputDto)
    fun getInput(): InputDto?
}