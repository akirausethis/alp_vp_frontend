package com.example.alp_front

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.alp_front.ui.theme.Alp_frontTheme
import com.example.alp_front.view.PanitiaApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Alp_frontTheme {
                // TODO: add routers here
                PanitiaApp()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    Alp_frontTheme  {
        PanitiaApp()
    }
}