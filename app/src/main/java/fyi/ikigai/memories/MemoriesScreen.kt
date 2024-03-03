package fyi.ikigai.memories

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fyi.ikigai.memories.auth.AuthViewModel
import fyi.ikigai.memories.ui.screens.AuthScreen
import fyi.ikigai.memories.ui.screens.HomeScreen
import fyi.ikigai.memories.ui.screens.LoginScreen

enum class MemoriesScreen(val title: String? = null) {
    Login(), Auth(), Home(title = "Memories")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoriesAppBar(
    currentScreen: MemoriesScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (currentScreen.title != null) {
        TopAppBar(title = { Text(currentScreen.title) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = modifier,
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            })
    }
}

@Composable
fun MemoriesApp(
    intentUri: Uri?,
    authViewModel: AuthViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MemoriesScreen.valueOf(
        backStackEntry?.destination?.route ?: MemoriesScreen.Login.name
    )
    val startDestination =
        if (intentUri == null) MemoriesScreen.Login.name else MemoriesScreen.Auth.name

    Scaffold(topBar = {
        MemoriesAppBar(currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() })
    }) { innerPadding ->
        NavHost(
            navController,
            startDestination,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = MemoriesScreen.Login.name) {
                val context = LocalContext.current
                LoginScreen(modifier = Modifier.fillMaxSize(),
                    onLoginWithStravaClicked = { loginWithStrava(context) })
            }
            composable(route = MemoriesScreen.Home.name) {
                HomeScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = MemoriesScreen.Auth.name) {
                AuthScreen(modifier = Modifier.fillMaxSize(),
                    intentUri,
                    authViewModel,
                    showHome = { navController.navigate(MemoriesScreen.Home.name) },
                    showLogin = { navController.navigate(MemoriesScreen.Login.name) })
            }
        }
    }
}

private fun loginWithStrava(context: Context) {
    val intentUri = Uri.parse("https://www.strava.com/oauth/mobile/authorize").buildUpon()
        .appendQueryParameter("client_id", BuildConfig.STRAVA_CLIENT_ID)
        .appendQueryParameter("redirect_uri", "fyi.ikigai.memories://ikigai.fyi")
        .appendQueryParameter("response_type", "code")
        .appendQueryParameter("approval_prompt", "auto")
        .appendQueryParameter("scope", "activity:write,read").build()
    val intent = Intent(Intent.ACTION_VIEW, intentUri)

    context.startActivity(intent)
}