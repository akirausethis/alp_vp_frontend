package com.mdanielelel.danielfrontend.ui.pages.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mdanielelel.danielfrontend.R
import com.mdanielelel.danielfrontend.navigation.Pages
import com.mdanielelel.danielfrontend.ui.components.InputFieldComponent
import com.mdanielelel.danielfrontend.ui.components.SvgIconComposable
import com.mdanielelel.danielfrontend.ui.theme.DanielFrontEndTheme

@Composable
fun LoginPage(
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val email by viewModel.email
    val password by viewModel.password

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight / 1.7f)
                .clip(RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp))
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(top = 24.dp, start = 24.dp, end = 24.dp)
        ) {
            Text(
                text = "Login", style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            InputFieldComponent(
                label = "Email",
                icon = { SvgIconComposable(resourceId = R.raw.ic_email, color = Color.White) },
                value = email,
                onValueChange = viewModel::onEmailChange
            )
            Spacer(modifier = Modifier.height(24.dp))
            InputFieldComponent(
                label = "Password",
                icon = { SvgIconComposable(resourceId = R.raw.ic_lock, color = Color.White) },
                value = password,
                obscureText = true,
                onValueChange = viewModel::onPasswordChange
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    navController.navigate(Pages.Home.route) {
                        popUpTo(Pages.Login.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Login", style = MaterialTheme.typography.labelLarge)
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "New to UCNews?",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                TextButton(onClick = {
                    navController.navigate(Pages.Register.route) {
                        popUpTo(Pages.Login.route) {
                            inclusive = true
                        }
                    }
                }) {
                    Text(
                        text = "Sign up",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Black
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    DanielFrontEndTheme {
//        LoginPage()
    }
}