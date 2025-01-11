package com.example.alp_vp.view.CREATE.Form

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_vp.R

@Composable
fun NewsChoiceScreen(modifier: Modifier = Modifier) {
    Box(
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
        )

        // Title Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create Event",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "Please fill out the form!",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp
                )
            )
        }

        // Add ProfileFormScreen and Submit Button
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp) // Agar tidak tertumpuk dengan top box
        ) {
            Box(modifier = Modifier.weight(1f)) {
                NewsFormScreen()
            }
        }

        // Bottom NavBar and Submit Button
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Button(
                onClick = {
                    // Handle submit action
                },
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B00))
            ) {
                Text(
                    text = "Submit",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            NewsBottomNavBar()
        }
    }
}

@Composable
fun NewsInputFormField(
    icon: Int,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isMultiline: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(28.dp) // Ukuran kotak latar belakang
                    .background(Color(0xFFFF6B00), shape = RoundedCornerShape(8.dp)) // Warna latar dan rounded corner
                    .padding(6.dp), // Padding di dalam kotak latar untuk memberikan ruang bagi ikon
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = label,
                    tint = Color.White, // Warna ikon menjadi putih
                    modifier = Modifier.size(24.dp) // Ukuran ikon
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF000000)
                )
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        // Multiline Text Field
        if (isMultiline) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp) // Tentukan tinggi untuk input multiline
                    .background(Color(0xFFF8F8F8), shape = RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.fillMaxSize(),
                    textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                    cursorBrush = SolidColor(Color(0xFFFF6B00))
                )
            }
        } else {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF8F8F8), shape = RoundedCornerShape(8.dp))
                    .padding(12.dp),
                textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                cursorBrush = SolidColor(Color(0xFFFF6B00))
            )
        }
    }
}

@Composable
fun NewsFormScreen(modifier: Modifier = Modifier) {
    // State for the form inputs
    var newsName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            NewsInputFormField(
                icon = R.drawable.baseline_person_24,
                label = "News Title",
                value = newsName,
                onValueChange = { newsName = it }
            )
        }
        item {
            NewsInputFormField(
                icon = R.drawable.baseline_badge_24,
                label = "Description",
                value = description,
                onValueChange = { description = it },
                isMultiline = true
            )
        }
        item { Spacer(modifier = Modifier.height(100.dp)) }
    }
}

@Composable
fun NewsBottomNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(104.dp)
            .padding(bottom = 28.dp)
            .background(Color(0xFFFFFFFF)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NewsBottomNavItem(
            iconRes = R.drawable.baseline_home_24,
            label = "Home"
        )
        NewsBottomNavItem(
            iconRes = R.drawable.baseline_event_note_24,
            label = "Event"
        )

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFFF6B00), shape = RoundedCornerShape(12.dp))
                .clickable {
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = "Add",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        NewsBottomNavItem(
            iconRes = R.drawable.baseline_library_books_24,
            label = "IO/CR"
        )
        NewsBottomNavItem(
            iconRes = R.drawable.baseline_history_24,
            label = "History"
        )
    }
}

@Composable
fun NewsBottomNavItem(iconRes: Int, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = Color(0xFF000000),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF000000)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun NewsChoicePreview() {
    NewsChoiceScreen()
}
