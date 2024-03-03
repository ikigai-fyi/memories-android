package fyi.ikigai.memories.ui.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fyi.ikigai.memories.R
import fyi.ikigai.memories.auth.AuthViewModel
import fyi.ikigai.memories.ui.theme.StravaBackground

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginWithStravaClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.ikigai),
                contentDescription = "Ikigai logo",
                modifier = Modifier
                    .clip(RoundedCornerShape(40.dp))
                    .size(200.dp),
            )
            Text(text = "Memories", fontSize = 30.sp, fontWeight = FontWeight.Black)
            Text(
                text = "Strava",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .rotate(180F)
                    .scale(-1.0F, 1F)
            )
        }
        Button(
            onClick = { onLoginWithStravaClicked() }, colors = ButtonDefaults.buttonColors(
                containerColor = StravaBackground, contentColor = Color.White
            )

        ) {
            Text(text = "Connect with Strava")
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    val auth = AuthViewModel()

    LoginScreen()
}