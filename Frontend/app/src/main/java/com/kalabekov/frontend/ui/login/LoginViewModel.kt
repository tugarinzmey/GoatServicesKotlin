package com.kalabekov.frontend.ui.login
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kalabekov.frontend.auth.data.LoginRepository
import com.kalabekov.frontend.R
import com.kalabekov.frontend.auth.StaticTokenManager
import com.kalabekov.frontend.auth.LoginRequest
import com.kalabekov.frontend.auth.LoginResponse
import com.kalabekov.frontend.dao.ApiBuilder



class LoginViewModel( private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult


     suspend fun login(username: String, password: String) {
        val apiService = ApiBuilder.buildApiAuth()

        val requestBody = LoginRequest(username,password)

        val response = apiService.login(requestBody)
        if(response.isSuccessful){
            val loginResponse:LoginResponse? = response.body()
            val token = loginResponse?.token
            StaticTokenManager.saveToken(token!!)
            _loginResult.value = LoginResult(success = LoggedInUserView(displayName = token))

        } else {
            val regResponse = apiService.registration(requestBody)
            if(regResponse.isSuccessful){
                _loginResult.value = LoginResult(regStatus =  true)

            }
            else{
                _loginResult.value = LoginResult(error = R.string.login_failed)

            }

        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
       return username.isNotEmpty()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length in 6..50
    }


}