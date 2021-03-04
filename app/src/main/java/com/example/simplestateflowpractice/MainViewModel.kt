package com.example.simplestateflowpractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel : ViewModel() {

    private var _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val loginUiState :StateFlow<LoginUiState> = _loginUiState

    fun loginUser(username: String, password: String) = viewModelScope.launch {

        _loginUiState.value = LoginUiState.Loading
        delay(2000L)

        if(username == "amirreza" && password== "12345"){
            _loginUiState.value = LoginUiState.Success
        }else{
            _loginUiState.value = LoginUiState.Error("Wrong credentials")
        }

    }

}