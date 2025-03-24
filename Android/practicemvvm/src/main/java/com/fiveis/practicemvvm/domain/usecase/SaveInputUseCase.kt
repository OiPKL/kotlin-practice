package com.fiveis.practicemvvm.domain.usecase

import com.fiveis.practicemvvm.data.dto.InputDto
import com.fiveis.practicemvvm.domain.repository.InputRepository

class SaveInputUseCase(private val repository: InputRepository) {
    fun execute(input: String) {
        repository.saveInput(InputDto(input))
    }
}