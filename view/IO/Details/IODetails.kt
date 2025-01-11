package com.example.alp_vp.view.IO.Details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.compose.ui.zIndex
import com.example.alp_vp.R
import com.example.alp_vp.view.KPCR.Details.IOCardDetails

@Composable
fun IOScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        // Orange Top Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    color = Color(0xFFFF6B00),
                    shape = RoundedCornerShape(
                        bottomStart = 64.dp,
                        bottomEnd = 64.dp
                    )
                )
        )

        // Back Button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp, top = 42.dp)
                .align(Alignment.TopStart)
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier.size(35.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "Back Icon",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "Back",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        // White Main Box
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-200).dp)
                .height(275.dp)
                .width(300.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .zIndex(1f)
        ) {
            // Black Inner Box
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-50).dp)
                    .height(225.dp)
                    .width(300.dp)
                    .background(
                        color = Color(0xFF333333),
                        shape = RoundedCornerShape(16.dp)
                    )
            )
        }

        // Title Section: "Career Center" at the Top
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(2f)
        ) {
            Text(
                text = "International Office",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.offset(y = 323.dp)
            )

            Text(
                text = "Details",
                style = TextStyle(
                    color = Color(0xFF555555),
                    fontSize = 10.sp
                ),
                modifier = Modifier.offset(y = 324.dp)
            )
        }

        // Main Content Area
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 150.dp)
        ) {

            Spacer(modifier = Modifier.height(35.dp))

            // LazyColumn for CardDetails with Scroll
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp) // Limit height to make it scrollable
            ) {
                item {
                    IOCardDetails(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 125.dp) // Adjust top padding to avoid overlap
                    )
                }
            }

            // REGISTER NOW Button
            Button(
                onClick = { /* Handle REGISTER NOW button click */ },
                modifier = Modifier
                    .padding(horizontal = 48.dp, vertical = 16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF6B00),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(7.dp)
            ) {
                Text(text = "REGISTER NOW")
            }
            IOBottomNavBar()
        }
    }
}

@Composable
fun IOBottomNavBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFFFFFFFF)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IOBottomNavItem(
            iconRes = R.drawable.baseline_home_24,
            label = "Home"
        )
        IOBottomNavItem(
            iconRes = R.drawable.baseline_event_note_24,
            label = "Event"
        )

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFFF6B00), shape = RoundedCornerShape(8.dp))
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

        IOBottomNavItem(
            iconRes = R.drawable.baseline_library_books_24,
            label = "IO/CR"
        )
        IOBottomNavItem(
            iconRes = R.drawable.baseline_history_24,
            label = "History"
        )
    }
}

@Composable
fun IOBottomNavItem(iconRes: Int, label: String) {
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
fun IOPreview() {
    IOScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}