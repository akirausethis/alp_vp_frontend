import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mdanielelel.danielfrontend.ui.components.EventListItem

@Composable
fun EventPage(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val recruitmentList = listOf(
        Pair("Jurusan", "Fikom"),
        Pair("Jurusan", "Teknik Informatika"),
        Pair("Jurusan", "Sistem Informasi"),
        Pair("Jurusan", "Teknik Elektro"),
        Pair("Jurusan", "Teknik Mesin"),
        Pair("Jurusan", "Teknik Industri"),
        Pair("Jurusan", "Arsitektur"),
        Pair("Jurusan", "Ilmu Komunikasi"),
        Pair("Jurusan", "Desain Produk"),
        Pair("Jurusan", "Psikologi"),
        Pair("Jurusan", "Manajemen"),
        Pair("Jurusan", "Akuntansi"),
        Pair("Jurusan", "Ilmu Hukum"),
        Pair("Jurusan", "Ilmu Pemerintahan"),
        Pair("Jurusan", "Keperawatan"),
        Pair("Jurusan", "Kedokteran"),
        Pair("Jurusan", "Farmasi"),
        Pair("Jurusan", "Matematika"),
        Pair("Jurusan", "Fisika"),
        Pair("Jurusan", "Biologi"),
        Pair("Jurusan", "Kimia")
    )



    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(92.dp),
                color = Color(0xFFFF4C00),
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "Back",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp
                        )
                    )
                }
            }

            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    "See All Available Events",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Black
                    ),
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp
                    )
                )
                Text(
                    "All Across the Majors",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 180.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f)
                ) {
                    items(recruitmentList) { recruitment ->
                        EventListItem(
                            title = recruitment.first,
                            subtitle = recruitment.second,
                            onClick = {

                            }
                        )
                    }
                }
            }
        }
    }
}
