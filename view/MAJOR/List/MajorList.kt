package com.example.alp_vp.view.MAJOR.List

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_vp.view.BottomNavBar
import com.example.alp_vp.view.MAJOR.Card.MajorListCard

data class Major(val name: String, val code: String)

val majorsList = listOf(
    Major("IMT", "1"),
    Major("Teknik Informatika", "2"),
    Major("Manajemen", "3"),
    Major("Akuntansi", "4"),
    Major("Teknik Sipil", "5"),
    Major("Arsitektur", "6"),
    Major("Hukum", "7"),
    Major("Psikologi", "8"),
    Major("Ilmu Komunikasi", "9"),
    Major("Desain Komunikasi Visual", "10"),
    Major("Bioteknologi", "11"),
    Major("Ekonomi Pembangunan", "12")
)

@Composable
fun MajorListScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        // Orange Top Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    color = Color(0xFFFF6B00),
                    shape = RoundedCornerShape(
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )
                )
        ) {
            // Title Section inside the Orange Box
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp), // Adjust padding to position text within the box
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hello There!",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Which major you are looking for?",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp
                    )
                )
            }
        }

        // LazyColumn for the 12 Major Cards with weight to take the remaining space
        LazyColumn(
            modifier = Modifier
                .weight(1f) // Ensures it takes the remaining space between title and BottomNavBar
                .padding(top = 16.dp) // Adjust padding to give space from the title
        ) {
            items(majorsList.size) { index ->
                MajorListCard(major = majorsList[index])
            }
        }

        // Bottom navigation bar should now be at the bottom
        BottomNavBar(modifier = Modifier)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MajorListPreview() {
    MajorListScreen()
}


