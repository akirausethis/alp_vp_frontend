package com.example.alp_vp.view

// Import
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_vp.R

// Navbar Composable
@Composable
fun NavBar(modifier: Modifier) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Home Button
        Button(
            onClick = { },
            modifier = Modifier
                .background(Color.White) // Tambahkan warna putih jika perlu
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_home_24),
                    contentDescription = "Home"
                )
                Text(
                    text = "Home",
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        }

        // Event IconButton
        IconButton(
            onClick = { }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_event_note_24),
                    contentDescription = "Event"
                )
                Text(
                    text = "Event",
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        }

        // Add IconButton
        IconButton(
            onClick = { }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = "Add",
                tint = Color(0xFFFF6B6B)
            )
        }

        // IO/CR IconButton
        IconButton(
            onClick = { }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_library_books_24),
                    contentDescription = "IO/CR"
                )
                Text(
                    text = "IO/CR",
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        }

        // History IconButton
        IconButton(
            onClick = { }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_history_24),
                    contentDescription = "History"
                )
                Text(
                    text = "History",
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun NavBarPreview() {
    NavBar(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}
