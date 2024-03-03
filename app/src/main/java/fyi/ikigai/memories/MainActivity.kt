package fyi.ikigai.memories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val intentUri = intent.data.also { intent.data = null }

        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        setContent {
            MemoriesApp(intentUri)
        }
    }
}
