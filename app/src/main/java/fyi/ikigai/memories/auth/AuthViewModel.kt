package fyi.ikigai.memories.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AuthViewModel() : ViewModel() {
    var userIsAuthenticated by mutableStateOf(false)

    fun login() {
        userIsAuthenticated = true
    }

    fun logout() {
        userIsAuthenticated = false
    }
}