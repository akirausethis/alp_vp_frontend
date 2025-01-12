package com.example.alp_front.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.alp_front.R
import com.example.alp_front.enums.PagesEnum
import com.example.alp_front.ui.theme.Alp_frontTheme
import com.example.alp_front.uiStates.AuthenticationStatusUIState
import com.example.alp_front.viewmodel.AuthenticationViewModel
import com.example.alp_front.view.templates.AuthenticationButton
import com.example.alp_front.view.templates.AuthenticationQuestion
import com.example.alp_front.view.templates.AuthenticationOutlinedTextField
import com.example.alp_front.view.templates.PasswordOutlinedTextField

@Composable
fun RegisterView(
    authenticationViewModel: AuthenticationViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    context: Context
) {
    val registerUIState by authenticationViewModel.authenticationUIState.collectAsState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(authenticationViewModel.dataStatus) {
        val dataStatus = authenticationViewModel.dataStatus
        if (dataStatus is AuthenticationStatusUIState.Failed) {
            Toast.makeText(context, dataStatus.errorMessage, Toast.LENGTH_SHORT).show()
            authenticationViewModel.clearErrorMessage()
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "WELCOME TO",
                fontSize = 35.sp,
                fontWeight = FontWeight.Light
            )

            Text(
                text = "TODO LIST",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AuthenticationOutlinedTextField(
                inputValue = authenticationViewModel.emailInput,
                onInputValueChange = {
                    authenticationViewModel.changeEmailInput(it)
                    authenticationViewModel.checkRegisterForm()
                },
                labelText = stringResource(id = R.string.emailText),
                placeholderText = stringResource(id = R.string.emailText),
                leadingIconSrc = painterResource(id = R.drawable.baseline_email_24),
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardType = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                onKeyboardNext = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )

            Spacer(modifier = Modifier.padding(5.dp))

            AuthenticationOutlinedTextField(
                inputValue = authenticationViewModel.usernameInput,
                onInputValueChange = {
                    authenticationViewModel.changeUsernameInput(it)
                    authenticationViewModel.checkRegisterForm()
                },
                labelText = stringResource(id = R.string.usernameText),
                placeholderText = stringResource(id = R.string.usernameText),
                leadingIconSrc = painterResource(id = R.drawable.baseline_drive_file_rename_outline_24),
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardType = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                onKeyboardNext = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )

            Spacer(modifier = Modifier.padding(5.dp))

            // TODO: use the pre-made template for password input design from the AuthenticationTemplate file
            PasswordOutlinedTextField(
                passwordInput = authenticationViewModel.passwordInput,
                onPasswordInputValueChange = {
                    authenticationViewModel.changePasswordInput(it)
                    authenticationViewModel.checkRegisterForm()
                },
                passwordVisibilityIcon = painterResource(id = registerUIState.passwordVisibilityIcon),
                labelText = stringResource(id = R.string.passwordText),
                placeholderText = stringResource(id = R.string.passwordText),
                onTrailingIconClick = {
                    authenticationViewModel.changePasswordVisibility()
                },
                passwordVisibility = registerUIState.passwordVisibility,
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardImeAction = ImeAction.Next,
                onKeyboardNext = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )

            Spacer(modifier = Modifier.padding(5.dp))

            // TODO: use the pre-made template for password input design from the AuthenticationTemplate file
            PasswordOutlinedTextField(
                passwordInput = authenticationViewModel.confirmPasswordInput,
                onPasswordInputValueChange = {
                    authenticationViewModel.changeConfirmPasswordInput(it)
                    authenticationViewModel.checkRegisterForm()
                },
                passwordVisibilityIcon = painterResource(id = registerUIState.confirmPasswordVisibilityIcon),
                labelText = stringResource(id = R.string.confirm_password_text),
                placeholderText = stringResource(id = R.string.confirm_password_text),
                onTrailingIconClick = {
                    authenticationViewModel.changeConfirmPasswordVisibility()
                },
                passwordVisibility = registerUIState.confirmPasswordVisibility,
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardImeAction = ImeAction.None,
                onKeyboardNext = KeyboardActions(
                    onDone = null
                )
            )

            AuthenticationButton(
                buttonText = stringResource(id = R.string.registerText),
                onButtonClick = {
                    authenticationViewModel.registerUser(navController)
                },
                buttonModifier = Modifier
                    .padding(top = 30.dp),
                textModifier = Modifier
                    .padding(vertical = 5.dp, horizontal = 15.dp),
                buttonEnabled = registerUIState.buttonEnabled,
                buttonColor = authenticationViewModel.checkButtonEnabled(registerUIState.buttonEnabled),
                userDataStatusUIState = authenticationViewModel.dataStatus,
                loadingBarModifier = Modifier
                    .padding(top = 30.dp)
                    .size(40.dp)
            )
        }

        AuthenticationQuestion(
            questionText = stringResource(id = R.string.already_have_an_account_text),
            actionText = stringResource(id = R.string.sign_in_text),
            onActionTextClicked = {
                authenticationViewModel.resetViewModel()
                navController.navigate(PagesEnum.Login.name) {
                    popUpTo(PagesEnum.Register.name) {
                        inclusive = true
                    }
                }
            },
            rowModifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun RegisterViewPreview() {
    Alp_frontTheme {
        RegisterView(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            authenticationViewModel = viewModel(factory = AuthenticationViewModel.Factory),
            navController = rememberNavController(),
            context = LocalContext.current
        )
    }
}