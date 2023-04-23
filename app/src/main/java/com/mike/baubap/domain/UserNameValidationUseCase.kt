package com.mike.baubap.domain

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class UserNameValidationUseCase @Inject constructor(
    private val repository: ValidationRepository
) {
    operator fun invoke(userName: StateFlow<Input>): Flow<Boolean> {
        return userName.map { user ->
            user.isInitialValue() || repository.isUserNameValid(user.value)
        }
    }
}