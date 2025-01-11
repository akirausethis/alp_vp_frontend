package com.example.alp_front.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.alp_front.R
import com.example.alp_front.TodoListApplication
import com.example.alp_front.enums.PagesEnum
import com.example.alp_front.models.ErrorModel
import com.example.alp_front.models.UserResponse
import com.example.alp_front.repositories.AuthenticationRepository
import com.example.alp_front.repositories.NetworkUserRepository
import com.example.alp_front.repositories.UserRepository
import com.example.alp_front.uiStates.AuthenticationStatusUIState
import com.example.alp_front.uiStates.AuthenticationUIState
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class AuthenticationViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _authenticationUIState = MutableStateFlow(AuthenticationUIState())
    val authenticationUIState: StateFlow<AuthenticationUIState> get() = _authenticationUIState.asStateFlow()

    var dataStatus: AuthenticationStatusUIState by mutableStateOf(AuthenticationStatusUIState.Start)
        private set

    var usernameInput by mutableStateOf("")
        private set
    var passwordInput by mutableStateOf("")
        private set
    var confirmPasswordInput by mutableStateOf("")
        private set
    var emailInput by mutableStateOf("")
        private set

    fun changeEmailInput(emailInput: String) { this.emailInput = emailInput }
    fun changeConfirmPasswordInput(confirmPasswordInput: String) { this.confirmPasswordInput = confirmPasswordInput }
    fun changeUsernameInput(usernameInput: String) { this.usernameInput = usernameInput }
    fun changePasswordInput(passwordInput: String) { this.passwordInput = passwordInput }

    fun changePasswordVisibility() {
        _authenticationUIState.update { currentState ->
            currentState.copy(
                showPassword = !currentState.showPassword,
                passwordVisibility = if (currentState.showPassword) PasswordVisualTransformation() else VisualTransformation.None,
                passwordVisibilityIcon = R.drawable.baseline_password_24
            )
        }
    }

    fun changeConfirmPasswordVisibility() {
        _authenticationUIState.update { currentState ->
            currentState.copy(
                showConfirmPassword = !currentState.showConfirmPassword,
                confirmPasswordVisibility = if (currentState.showConfirmPassword) PasswordVisualTransformation() else VisualTransformation.None,
                confirmPasswordVisibilityIcon = R.drawable.baseline_password_24
            )
        }
    }

    fun checkLoginForm() {
        _authenticationUIState.update {
            it.copy(buttonEnabled = emailInput.isNotEmpty() && passwordInput.isNotEmpty())
        }
    }

    fun checkRegisterForm() {
        _authenticationUIState.update {
            it.copy(buttonEnabled = emailInput.isNotEmpty() && passwordInput.isNotEmpty() && usernameInput.isNotEmpty() && confirmPasswordInput.isNotEmpty() && passwordInput == confirmPasswordInput)
        }
    }

    fun registerUser(navController: NavHostController) {
        viewModelScope.launch {
            dataStatus = AuthenticationStatusUIState.Loading
            try {
                val call = authenticationRepository.register(usernameInput, emailInput, passwordInput)
                call.enqueue(object : Callback<UserResponse> {
                    override fun onResponse(call: Call<UserResponse>, res: Response<UserResponse>) {
                        if (res.isSuccessful) {
                            val userData = res.body()!!.data
                            saveUsernameToken( userData.token, userData.username)
                            dataStatus = AuthenticationStatusUIState.Success(userData)
                            resetViewModel()
                            navController.navigate(PagesEnum.Home.name) {
                                popUpTo(PagesEnum.Register.name) { inclusive = true }
                            }
                        } else {
                            val errorMessage = Gson().fromJson(res.errorBody()!!.charStream(), ErrorModel::class.java)
                            dataStatus = AuthenticationStatusUIState.Failed(errorMessage.errors)
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        dataStatus = AuthenticationStatusUIState.Failed(t.localizedMessage)
                    }
                })
            } catch (error: IOException) {
                dataStatus = AuthenticationStatusUIState.Failed(error.localizedMessage)
            }
        }
    }

    fun loginUser(navController: NavHostController) {
        viewModelScope.launch {
            dataStatus = AuthenticationStatusUIState.Loading
            try {
                val call = authenticationRepository.login(emailInput, passwordInput)
                call.enqueue(object : Callback<UserResponse> {
                    override fun onResponse(call: Call<UserResponse>, res: Response<UserResponse>) {
                        if (res.isSuccessful) {
                            val userData = res.body()!!.data
                            saveUsernameToken(userData.token, userData.username)
                            dataStatus = AuthenticationStatusUIState.Success(userData)
                            resetViewModel()
                            navController.navigate(PagesEnum.Home.name) {
                                popUpTo(PagesEnum.Login.name) { inclusive = true }
                            }
                        } else {
                            val errorMessage = Gson().fromJson(res.errorBody()!!.charStream(), ErrorModel::class.java)
                            dataStatus = AuthenticationStatusUIState.Failed(errorMessage.errors)
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        dataStatus = AuthenticationStatusUIState.Failed(t.localizedMessage)
                    }
                })
            } catch (error: IOException) {
                dataStatus = AuthenticationStatusUIState.Failed(error.localizedMessage)
            }
        }
    }

    fun saveUsernameToken(token: String, username: String) {
        viewModelScope.launch {
            userRepository.saveUserToken(token)
            userRepository.saveUsername(username)
        }
    }

    fun checkButtonEnabled(isEnabled: Boolean): Color {
        if (isEnabled) {
            return Color.Blue
        }

        return Color.LightGray
    }

    fun resetViewModel() {
        changeEmailInput("")
        changePasswordInput("")
        changeUsernameInput("")
        changeConfirmPasswordInput("")
        _authenticationUIState.update {
            it.copy(
                showConfirmPassword = false,
                showPassword = false,
                passwordVisibility = PasswordVisualTransformation(),
                confirmPasswordVisibility = PasswordVisualTransformation(),
                passwordVisibilityIcon = R.drawable.baseline_password_24,
                confirmPasswordVisibilityIcon = R.drawable.baseline_password_24,
                buttonEnabled = false
            )
        }
        dataStatus = AuthenticationStatusUIState.Start
    }

    fun clearErrorMessage() {
        dataStatus = AuthenticationStatusUIState.Start
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TodoListApplication)
                val authenticationRepository = application.container.authenticationRepository
                val userRepository = application.container.userRepository
                AuthenticationViewModel(authenticationRepository, userRepository)
            }
        }
    }
}
