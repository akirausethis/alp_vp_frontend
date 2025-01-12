import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mdanielelel.danielfrontend.R
import com.mdanielelel.danielfrontend.navigation.Pages
import com.mdanielelel.danielfrontend.ui.components.NewsCardComponent
import com.mdanielelel.danielfrontend.ui.components.RecruitmentListItem

@Composable
fun HomePage(
    navController: NavController,
    modifier: Modifier = Modifier,
    username: String = "User"
) {
    Scaffold(
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            item {
                UserWelcomeSection(username)
            }

            item {
                HeaderContentComponent(
                    title = "Quotes of The Day",
                    subtitle = "The best way to predict the future is to create it. - Peter Drucker"
                )
            }

            item {
                SectionHeaderComponent(
                    title = "Open Recruitment",
                    onSeeAllClick = {
                        navController.navigate(Pages.Event.route)
                    }
                )
            }

            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    content = {
                        items(5) {
                            RecruitmentListItem(
                                title = "IMT",
                                subtitle = "20 Events",
                                onClick = { Log.d("HomePage", "IMT Clicked $it") }
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                SectionHeaderComponent(
                    title = "News",
                    onSeeAllClick = { /*TODO*/ }
                )
            }

            items(5) {
                NewsCardComponent(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 0.dp,
                        bottom = 12.dp
                    ),
                    imageRes = R.drawable.ic_launcher_background
                )
            }
        }
    }
}

@Composable
private fun UserWelcomeSection(username: String) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.avatar_example),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = username,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }

        IconButton(onClick = { /* Handle notification click */ }) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notifications",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun SectionHeaderComponent(
    title: String,
    onSeeAllClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(
                start = 0.dp,
                end = 16.dp,
                top = 0.dp,
                bottom = 8.dp
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(16.dp)
        )
        TextButton(
            interactionSource = remember { MutableInteractionSource() },
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.Black,
                containerColor = Color.Transparent
            ),
            onClick = onSeeAllClick
        ) {
            Text("See All", style = MaterialTheme.typography.bodyMedium)
        }
    }
}