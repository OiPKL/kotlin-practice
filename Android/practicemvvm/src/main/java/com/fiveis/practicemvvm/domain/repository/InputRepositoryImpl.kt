package com.fiveis.practicemvvm.domain.repository

import com.fiveis.practicemvvm.data.dto.InputDto

class InputRepositoryImpl : InputRepository {
    private var storedInput: InputDto? = null

    override fun saveInput(input: InputDto) {
        storedInput = input
    }

    override fun getInput(): InputDto? {
        return storedInput
    }
}