package com.example.alp_vp.view.OPREC.ListOPREC.Card

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
import com.example.alp_vp.R

@Composable
fun OprecListCard(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFF6F3E)),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(150.dp) // Tinggi tetap
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopStart) // Elemen teks berada di atas
            ) {
                // Placeholder untuk icon di sebelah kiri
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .background(Color(0xFF3E2723), shape = RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxHeight(), // Isi tinggi kolom
                    verticalArrangement = Arrangement.SpaceBetween // Atur elemen di kolom
                ) {
                    // Teks di atas
                    Column {
                        Text(
                            text = "Open Recruitment",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            ),
                            modifier = Modifier.padding(top = 8.dp) // Add padding top
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Jurusan: IMT",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.White
                            )
                        )
                    }
                }
            }

            // Tombol di bawah
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4F4F4)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .align(Alignment.BottomCenter) // Posisikan tombol di bagian bawah tengah
                    .padding(16.dp) // Jarak dari tepi bawah
                    .height(36.dp)
                    .fillMaxWidth() // Tombol penuh di lebar card
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_remove_red_eye_24),
                        contentDescription = "See More",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "See More",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Black),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun OprecListCardPreview() {
    OprecListCard()
}
