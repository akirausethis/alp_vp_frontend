package com.example.alp_vp.view.KPCR.ListKPCR.List

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_vp.R
import com.example.alp_vp.view.HISTORY.Card.HistoryListCard

@Composable
fun HistoryListScreen(modifier: Modifier = Modifier) {
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
                text = "History",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "Here are all your past events that you've joined",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp
                )
            )
        }

        // List Section
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp, start = 16.dp, end = 16.dp, bottom = 70.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                items(5) {
                    HistoryListCard()
                }
            }
        }

        // Bottom Navigation Bar
        HistoryBottomNavBar(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun HistoryBottomNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(104.dp)
            .padding(bottom = 28.dp)
            .background(Color(0xFFFFFFFF)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HistoryBottomNavItem(
            iconRes = R.drawable.baseline_home_24,
            label = "Home"
        )
        HistoryBottomNavItem(
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

        HistoryBottomNavItem(
            iconRes = R.drawable.baseline_library_books_24,
            label = "IO/CR"
        )
        HistoryBottomNavItem(
            iconRes = R.drawable.baseline_history_24,
            label = "History"
        )
    }
}

@Composable
fun HistoryBottomNavItem(iconRes: Int, label: String) {
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
fun HistoryListPreview() {
    HistoryListScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}
