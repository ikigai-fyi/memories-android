package fyi.ikigai.memories.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fyi.ikigai.memories.auth.AuthViewModel
import fyi.ikigai.memories.ui.theme.StravaBackground

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    intentUri: Uri? = Uri.EMPTY,
    authViewModel: AuthViewModel,
    showHome: () -> Unit = {},
    showLogin: () -> Unit = {}
) {
    if (intentUri == null) {
        if (authViewModel.userIsAuthenticated) {
            return showHome()
        }
        return showLogin()
    }

    Box(
        contentAlignment = Alignment.Center, modifier = modifier
    ) {
        CircularProgressIndicator(color = StravaBackground)
    }
    println(intentUri.getQueryParameter("code"))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AuthScreen(authViewModel = AuthViewModel())
}