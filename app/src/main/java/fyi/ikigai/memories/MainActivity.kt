package fyi.ikigai.memories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import fyi.ikigai.memories.auth.AuthViewModel
import fyi.ikigai.memories.ui.screens.HomeScreen
import fyi.ikigai.memories.ui.screens.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = AuthViewModel()

        setContent {
            if (auth.userIsAuthenticated) {
                HomeScreen()
            } else {
                LoginScreen(auth)
            }
        }
    }
}
