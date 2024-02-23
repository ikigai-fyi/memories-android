package fyi.ikigai.memories.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AuthViewModel: ViewModel() {
    var appJustLaunched by mutableStateOf(true)
    var userIsAuthenticated by mutableStateOf(false)

    fun login() {
        userIsAuthenticated = true
        appJustLaunched = false
    }

    fun logout() {
        userIsAuthenticated = false
    }
}