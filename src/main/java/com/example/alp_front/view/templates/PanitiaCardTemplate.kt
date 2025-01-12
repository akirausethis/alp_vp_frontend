package com.example.alp_front.view.templates

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_front.R
import com.example.alp_front.enums.PrioritiesEnum

@Composable
fun PanitiaCardTemplate(
    title: String,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit
) {
    Card(
        onClick = onCardClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(Color.White),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(8f)
            ) {
                Text(
                    text = title,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {


                    }

                    // kasi icon jam
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_access_time_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp),
                        )

                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_task_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp),
                        )


                    }
                }
            }

            Image(
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                contentDescription = null,
                modifier = Modifier
                    .size(15.dp)
                    .weight(0.5f)

            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PanitiaCardTemplatePreview() {
    PanitiaCardTemplate(
        title = "Print Hello World",
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onCardClick = {}
    )
}