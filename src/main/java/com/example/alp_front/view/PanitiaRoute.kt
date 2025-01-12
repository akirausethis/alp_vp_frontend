package com.example.alp_front.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alp_front.enums.PagesEnum
import com.example.alp_front.viewmodel.AuthenticationViewModel
import com.example.alp_front.viewmodel.HomeViewModel
import com.example.alp_front.viewmodel.PanitiaDetailViewModel
import com.example.alp_front.viewmodel.PanitiaFormViewModel

@Composable
fun PanitiaApp(
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
    panitiaFormViewModel: PanitiaFormViewModel = viewModel(factory = PanitiaFormViewModel.Factory),
    authenticationViewModel: AuthenticationViewModel = viewModel(factory = AuthenticationViewModel.Factory),
    panitiaDetailViewModel: PanitiaDetailViewModel = viewModel(factory = PanitiaDetailViewModel.Factory)
) {


    val localContext = LocalContext.current
    val token = homeViewModel.token.collectAsState()

    NavHost(navController = navController, startDestination = if(token.value != "Unknown" && token.value != "") {
        PagesEnum.Home.name
    } else {
        PagesEnum.Login.name
    }) {
        composable(route = PagesEnum.Login.name) {
            LoginView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                authenticationViewModel = authenticationViewModel,
                navController = navController,
                context = localContext
            )
        }

        composable(route = PagesEnum.Register.name) {
            RegisterView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                authenticationViewModel = authenticationViewModel,
                navController = navController,
                context = localContext
            )
        }

        composable(route = PagesEnum.Home.name) {
            HomeView(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                homeViewModel = homeViewModel,
                navController = navController,
                token = token.value,
                PanitiaDetailViewModel =  panitiaDetailViewModel,
                context = localContext
            )
        }

        composable(route = PagesEnum.CreatePanitia.name) {
            PanitiaFormView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                context = localContext,
                panitiaFormViewModel = panitiaFormViewModel,
                navController = navController,
                token = token.value,
                panitiaDetailViewModel = panitiaDetailViewModel
            )
        }

        composable(route = PagesEnum.PanitiaDetail.name) {
            PanitiaDetailView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                navController = navController,
                token = token.value,
                PanitiaDetailViewModel = panitiaDetailViewModel,
                context = localContext,
                PanitiaFormViewModel = panitiaFormViewModel
            )
        }
    }
}