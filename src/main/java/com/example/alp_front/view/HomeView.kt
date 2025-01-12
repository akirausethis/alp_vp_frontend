package com.example.alp_front.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.alp_front.R
import com.example.alp_front.enums.PagesEnum
import com.example.alp_front.uiStates.AuthenticationStatusUIState
import com.example.alp_front.uiStates.StringDataStatusUIState
import com.example.alp_front.uiStates.PanitiaDataStatusUIState
import com.example.alp_front.viewmodel.HomeViewModel
import com.example.alp_front.viewmodel.PanitiaDetailViewModel
import com.example.alp_front.view.templates.CircleLoadingDialog
import com.example.alp_front.view.templates.CircleLoadingTemplate
import com.example.alp_front.view.templates.PanitiaCardTemplate

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    PanitiaDetailViewModel: PanitiaDetailViewModel,
    token: String,
    context: Context
) {
//    val todoList = homeViewModel.todoModel.collectAsState()
    val username = homeViewModel.username.collectAsState()
    val logoutStatus = homeViewModel.logoutStatus
    val dataStatus = homeViewModel.dataStatus

    LaunchedEffect(token) {
        if (token != "Unknown") {
            homeViewModel.getAllPanitias(token)
        }
    }

    LaunchedEffect(logoutStatus) {
        if (logoutStatus is StringDataStatusUIState.Failed) {
            Toast.makeText(context, "LOGOUT ERROR: ${logoutStatus.errorMessage}", Toast.LENGTH_SHORT).show()
            homeViewModel.clearLogoutErrorMessage()
        }
    }

    LaunchedEffect(dataStatus) {
        if (dataStatus is PanitiaDataStatusUIState.Failed) {
            Toast.makeText(context, "DATA ERROR: ${dataStatus.errorMessage}", Toast.LENGTH_SHORT).show()
            homeViewModel.clearDataErrorMessage()
        }
    }

    if (logoutStatus is StringDataStatusUIState.Loading) {
        // show loading dialog
        CircleLoadingDialog(
            onDismissRequest = {

            }
        )
    } else {
        Column(
            modifier = modifier
        ) {
            Button(
                onClick = {
                    homeViewModel.logoutUser(token, navController)
                },
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .padding(end = 10.dp, top = 20.dp)
                    .size(45.dp),
                colors = ButtonDefaults.buttonColors(Color.Red),
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_logout_24),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(20.dp)
                )
            }

            Column(
                modifier = Modifier
                    .padding(top = 20.dp, start = 10.dp, bottom = 10.dp),
            ) {
                Text(
                    text = "Welcome",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black
                )
                Text(
                    text = username.value,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                colors = CardDefaults.cardColors(Color.Black)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 20.dp, end = 16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(start = 8.dp, bottom = 4.dp)
                            .fillMaxWidth(),

                        ) {
                        Text(
                            text = stringResource(R.string.today_tasks_text),
                            fontSize = 27.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )

                        Button(
                            onClick = {
                                navController.navigate(PagesEnum.CreatePanitia.name) {
                                    popUpTo(PagesEnum.Home.name) {
                                        inclusive = false
                                    }
                                }
                            },
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(Color.White),
                            modifier = Modifier
                                .size(40.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_add_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }
                    }

                    when (dataStatus) {
                        is PanitiaDataStatusUIState.Success -> if (dataStatus.data.isNotEmpty()) {
                            LazyColumn(
                                flingBehavior = ScrollableDefaults.flingBehavior(),
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            ) {
                                items(dataStatus.data) { panitia ->
                                    PanitiaCardTemplate(
                                        title = panitia.title,
                                        modifier = Modifier
                                            .padding(bottom = 12.dp),
                                        onCardClick = {
                                            PanitiaDetailViewModel.getPanitia(token, panitia.id, navController, false)
                                        }
                                    )
                                }
                            }
                        } else {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "No Task Found!",
                                    fontSize = 21.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        is PanitiaDataStatusUIState.Failed -> Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "No Task Found!",
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        else -> Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircleLoadingTemplate(
                                color = Color.White,
                                trackColor = Color.Transparent,
                            )
                        }
                    }
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
fun HomeViewPreview() {
    HomeView(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        homeViewModel = viewModel(factory = HomeViewModel.Factory),
        navController = rememberNavController(),
        token = "",
        PanitiaDetailViewModel = viewModel(factory = PanitiaDetailViewModel.Factory),
        context = LocalContext.current
    )
}