package com.mike.baubap.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val handle: SavedStateHandle
) : ViewModel() {
    val userName = handle.getStateFlow(NAME, InputWrapper())
    val password = handle.getStateFlow(PASSWORD, InputWrapper())

    val areInputsValid: StateFlow<Boolean> = combine(userName, password) { name, pass ->
        name.value.isNotEmpty() && pass.value.isNotEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIME_OUT),
        initialValue = false
    )

    fun updateName(input: String) {
        handle[NAME] = userName.value.copy(value = input)
    }

    fun updatePassword(input: String) {
        handle[PASSWORD] = password.value.copy(value = input)
    }

    private companion object {
        const val NAME = "name"
        const val PASSWORD = "pass"
        const val TIME_OUT = 5000L
    }

}