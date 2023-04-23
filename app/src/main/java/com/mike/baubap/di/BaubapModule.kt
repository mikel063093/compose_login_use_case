package com.mike.baubap.di

import com.mike.baubap.domain.InputValidationUseCase
import com.mike.baubap.domain.PasswordValidationUseCase
import com.mike.baubap.domain.UserNameValidationUseCase
import com.mike.baubap.domain.ValidationRepository
import com.mike.baubap.domain.ValidationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [BaubapModule.BindRepository::class])
@InstallIn(SingletonComponent::class)
object BaubapModule {

    @Provides
    @Singleton
    fun provideValidationUseCase(
        repository: ValidationRepository
    ): InputValidationUseCase {
        return InputValidationUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUserNameUseCase(
        repository: ValidationRepository
    ): UserNameValidationUseCase {
        return UserNameValidationUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePasswordUseCase(
        repository: ValidationRepository
    ): PasswordValidationUseCase {
        return PasswordValidationUseCase(repository)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindRepository {
        @Binds
        @Singleton
        fun bindRepository(
            repository: ValidationRepositoryImpl
        ): ValidationRepository
    }
}