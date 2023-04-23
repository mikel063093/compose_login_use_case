package com.mike.baubap.domain

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class PasswordValidationUseCase @Inject constructor(
    private val repository: ValidationRepository
) {
    operator fun invoke(password: StateFlow<InputWrapper>): Flow<Boolean> {
        return password.map { pass ->
            pass.isInitialValue() || repository.isPasswordValid(pass.value)
        }
    }
}