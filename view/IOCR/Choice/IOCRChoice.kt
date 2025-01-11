package com.example.alp_vp.view.CREATE.Choice

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_vp.R

@Composable
fun IOCRChoiceListScreen(modifier: Modifier = Modifier) {
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
                text = "Hello There!",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "What are you looking for?",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp
                )
            )
        }

        // Center Boxes Container
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 140.dp), // Adjusted padding to move up
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp) // Adjusted space
        ) {
            // Center Box 1
            IOCRCenterBox(
                title = "International Office",
                description = "All IO Events Here!",
                icon = R.drawable.baseline_arrow_forward_24
            )

            // Divider with "Pick One"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    color = Color.Gray,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Pick One",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Divider(
                    color = Color.Gray,
                    modifier = Modifier.weight(1f)
                )
            }

            // Center Box 2
            IOCRCenterBox(
                title = "Career Center",
                description = "All CR Events Here!",
                icon = R.drawable.baseline_arrow_forward_24
            )
        }

        // Bottom Navigation Bar
        IOCRChoiceBottomNavBar(modifier = Modifier.align(Alignment.BottomCenter))
    }
}


@Composable
fun IOCRCenterBox(title: String, description: String, icon: Int) {
    // Outer White Box
    Box(
        modifier = Modifier
            .height(265.dp)
            .width(300.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        // Black Inner Box
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (0).dp)
                .height(215.dp)
                .width(300.dp)
                .background(
                    color = Color(0xFF333333),
                    shape = RoundedCornerShape(16.dp)
                )
        )

        // Title, Description & Arrow in White Box
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = title,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = description,
                    style = TextStyle(
                        color = Color(0xFF555555),
                        fontSize = 10.sp
                    )
                )
            }

            Icon(
                painter = painterResource(id = icon), // Replace with your arrow icon
                contentDescription = "Arrow Right",
                tint = Color(0xFFFFFFFF),
                modifier = Modifier
                    .size(24.dp)
                    .background(Color(0xFFFF6B00), shape = RoundedCornerShape(4.dp))
                    .padding(4.dp)
            )
        }
    }
}

@Composable
fun IOCRChoiceBottomNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(104.dp)
            .padding(bottom = 28.dp)
            .background(Color(0xFFFFFFFF)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IOCRChoiceBottomNavItem(
            iconRes = R.drawable.baseline_home_24,
            label = "Home"
        )
        IOCRChoiceBottomNavItem(
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

        IOCRChoiceBottomNavItem(
            iconRes = R.drawable.baseline_library_books_24,
            label = "IO/CR"
        )
        IOCRChoiceBottomNavItem(
            iconRes = R.drawable.baseline_history_24,
            label = "History"
        )
    }
}

@Composable
fun IOCRChoiceBottomNavItem(iconRes: Int, label: String) {
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
fun IOCRChoiceListPreview() {
    IOCRChoiceListScreen()
}
