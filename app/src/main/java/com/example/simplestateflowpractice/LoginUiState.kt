package com.example.simplestateflowpractice

sealed class LoginUiState {

    object Success : LoginUiState()
    data class Error(val message: String) : LoginUiState()

    object Loading : LoginUiState()
    object Empty : LoginUiState()

}
