package com.example.alp_vp.view.MAJOR.Card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_vp.R
import com.example.alp_vp.view.MAJOR.List.Major

@Composable
fun MajorListCard(major: Major) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        // Kotak tambahan di atas Row
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color(0xFFE0E0E0), RoundedCornerShape(16.dp)), // Tambahan RoundedCornerShape
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Header Section", // Show major name
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(8.dp)) // Tambahkan jarak antara Box dan Row

        // Row untuk Arrow, dan Icon Lain
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(16.dp)) // RoundedCornerShape di Row
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Section
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color(0xFFFF5722), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_account_balance_24), // Replace with your icon
                    contentDescription = "University Icon",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Text Section
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Jurusan",
                    style = MaterialTheme.typography.bodySmall, // Material 3 typography
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = "${major.name}",
                    style = MaterialTheme.typography.titleMedium, // Material 3 typography
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Arrow Icon
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color(0xFFFF5722), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_24), // Replace with your icon
                    contentDescription = "Arrow Icon",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
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
fun MajorListCardPreview() {
    MajorListCard(major = Major("IMT", "1"))
}
