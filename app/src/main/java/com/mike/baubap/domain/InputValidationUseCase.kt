package com.mike.baubap.domain

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine

class InputValidationUseCase @Inject constructor(
    private val repository: ValidationRepository
) {
    operator fun invoke(
        userName: StateFlow<Input>,
        password: StateFlow<Input>
    ): Flow<Boolean> {
        return combine(userName, password) { name, pass ->
            name.isInitialValue().not() && pass.isInitialValue().not() &&
            repository.isUserNameValid(name.value) &&
            repository.isPasswordValid(pass.value)
        }
    }
}