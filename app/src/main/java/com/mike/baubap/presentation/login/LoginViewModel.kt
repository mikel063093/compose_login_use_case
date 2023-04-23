package com.mike.baubap.presentation.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mike.baubap.domain.InputValidationUseCase
import com.mike.baubap.domain.Input
import com.mike.baubap.domain.PasswordValidationUseCase
import com.mike.baubap.domain.UserNameValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val timeMachine: SavedStateHandle,
    private val validationUseCase: InputValidationUseCase,
    private val userNameValidationUseCase: UserNameValidationUseCase,
    private val passwordValidationUseCase: PasswordValidationUseCase,
) : ViewModel() {

    internal val userName: StateFlow<Input> = timeMachine.getStateFlow(NAME, Input())
    internal val password: StateFlow<Input> =
        timeMachine.getStateFlow(PASSWORD, Input())

    fun isValidUserName() = userNameValidationUseCase.invoke(userName).toStateFlow(true)

    fun isValidPassword() = passwordValidationUseCase.invoke(password).toStateFlow(true)

    fun areInputValid() = validationUseCase(userName, password).toStateFlow(false)

    internal fun updateName(input: String) {
        timeMachine[NAME] = userName.value.copy(value = input)
    }

    internal fun updatePassword(input: String) {
        timeMachine[PASSWORD] = password.value.copy(value = input)
    }

    private fun <T> Flow<T>.toStateFlow(initialValue: T): StateFlow<T> {
        return this.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = initialValue
        )
    }

    private companion object {
        const val NAME = "name"
        const val PASSWORD = "pass"
    }
}