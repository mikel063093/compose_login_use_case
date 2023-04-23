package com.mike.baubap.domain

import javax.inject.Inject

interface ValidationRepository {
    fun isUserNameValid(userName: String?): Boolean
    fun isPasswordValid(password: String?): Boolean
}

class ValidationRepositoryImpl @Inject constructor() : ValidationRepository {
    override fun isUserNameValid(userName: String?): Boolean {
        return (userName?.length ?: 0) >= MINIMUM_LENGTH
    }

    override fun isPasswordValid(password: String?): Boolean {
        return(password?.length ?: 0) >= MINIMUM_LENGTH
    }

    private companion object {
        const val MINIMUM_LENGTH = 4
    }

}