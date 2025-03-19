package com.fiveis.practicemvvm.presentation.ui.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiveis.practicemvvm.domain.repository.InputRepositoryImpl
import com.fiveis.practicemvvm.domain.usecase.SaveInputUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InputViewModel : ViewModel() {
    private val repository = InputRepositoryImpl()
    private val saveInputUseCase = SaveInputUseCase(repository)

    private val _inputText = MutableStateFlow("")
    val inputText: StateFlow<String> get() = _inputText

    fun saveInput(text: String) {
        saveInputUseCase.execute(text)
        _inputText.value = repository.getInput()?.text ?: ""
    }
}