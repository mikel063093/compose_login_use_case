package com.mike.baubap.presentation.login

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import app.cash.turbine.testIn
import com.mike.baubap.domain.InputValidationUseCase
import com.mike.baubap.domain.PasswordValidationUseCase
import com.mike.baubap.domain.UserNameValidationUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalTime
@ExperimentalCoroutinesApi
class LoginViewModelTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    private val timeMachine = SavedStateHandle()

    @MockK
    private lateinit var validationUseCase: InputValidationUseCase

    @MockK
    private lateinit var userNameValidationUseCase: UserNameValidationUseCase

    @MockK
    private lateinit var passwordValidationUseCase: PasswordValidationUseCase

    private lateinit var viewModel: LoginViewModel

    private val testDispatcher = StandardTestDispatcher(name = "Test dispatcher")

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel(
            timeMachine = timeMachine,
            validationUseCase = validationUseCase,
            userNameValidationUseCase = userNameValidationUseCase,
            passwordValidationUseCase = passwordValidationUseCase
        )
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should emit correct values when updateName is called`() = runTest {
        // Given
        val newName = "Alice"
        // When
        viewModel.updateName(newName)
        // Then
        viewModel.userName.test {
            Assert.assertTrue(awaitItem().value.equals(newName, true))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should emit correct values when updatePassword is called`() = runTest {
        // Given
        val newPassword = "passw0rd"
        // When
        viewModel.updatePassword(newPassword)
        // Then
        viewModel.password.test {
            Assert.assertTrue(awaitItem().value.equals(newPassword, true))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `is ValidUserName should return True When UserName Is Valid`() = runTest {
        // Given
        val flow = flowOf(true)
        every { userNameValidationUseCase.invoke(any()) } returns flow
        // When
        val turbine = viewModel.isValidUserName().testIn(backgroundScope)
        // Then
        verify() { userNameValidationUseCase.invoke(any()) }
        assertTrue(turbine.awaitItem())
        turbine.cancelAndIgnoreRemainingEvents()
    }

    @Test
    fun `is ValidUserName should return False When UserName Is invalid`() = runTest {
        // Given
        val flow = flowOf(false)
        every { userNameValidationUseCase.invoke(any()) } returns flow
        // When
        val turbine = viewModel.isValidUserName().testIn(backgroundScope)
        // Then
        turbine.skipItems(1)
        verify() { userNameValidationUseCase.invoke(any()) }
        assertFalse(turbine.awaitItem())
        turbine.cancelAndIgnoreRemainingEvents()
    }

    @Test
    fun `is ValidPassword should return True When Password Is Valid`() = runTest {
        // Given
        every { passwordValidationUseCase.invoke(any()) } returns flow {
            emit(true)
        }
        // When
        val turbine = viewModel.isValidPassword().testIn(backgroundScope)
        // Then
        verify(exactly = 1) { passwordValidationUseCase.invoke(any()) }
        assertTrue(turbine.awaitItem())
        turbine.cancelAndIgnoreRemainingEvents()
    }

    @Test
    fun `is ValidPassword should return False When Password is invalid`() = runTest {
        // Given
        every { passwordValidationUseCase.invoke(any()) } returns flow {
            emit(false)
        }
        // When
        val turbine = viewModel.isValidPassword().testIn(backgroundScope)
        // Then
        turbine.skipItems(1)
        verify(exactly = 1) { passwordValidationUseCase.invoke(any()) }
        assertFalse(turbine.awaitItem())
        turbine.cancelAndIgnoreRemainingEvents()
    }


    @Test
    fun `Are InputValid should return True When inputs are valid`() = runTest {
        // Given
        every { validationUseCase.invoke(any(), any()) } returns flow {
            emit(true)
        }
        viewModel.updatePassword("pass")
        viewModel.updateName("name")
        // When

        val turbine = viewModel.areInputValid().testIn(backgroundScope)
        // Then
        turbine.skipItems(1)
        verify(exactly = 1) { validationUseCase.invoke(any(), any()) }
        assertTrue(turbine.awaitItem())
        turbine.cancelAndIgnoreRemainingEvents()
    }

    @Test
    fun `Are InputValid should return False When inputs are valid`() = runTest {
        // Given
        every { validationUseCase.invoke(any(), any()) } returns flow {
            emit(false)
        }
        viewModel.updatePassword("")
        viewModel.updateName("")
        // When
        val turbine = viewModel.areInputValid().testIn(backgroundScope)
        // Then
        verify(exactly = 1) { validationUseCase.invoke(any(), any()) }
        assertFalse(turbine.awaitItem())
        turbine.cancelAndIgnoreRemainingEvents()
    }
}
