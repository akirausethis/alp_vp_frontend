package com.example.alp_vp.view.LOGREG

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFA500)), // Background orange
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F1F1F) // Dark text color
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Email Input
                InputField(
                    label = "Email",
                    iconRes = android.R.drawable.ic_dialog_email
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password Input
                InputField(
                    label = "Password",
                    iconRes = android.R.drawable.ic_lock_lock,
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Login Button
                Button(
                    onClick = { /* Handle login action */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFA500), // Material3 uses containerColor
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "LOGIN")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Register Text
                Row(
                    verticalAlignment = Alignment.CenterVertically // Ensure proper vertical alignment
                ) {
                    Text(text = "New to this App?")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Register",
                        style = TextStyle(color = Color(0xFF1F1F1F)),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


@Composable
fun InputField(
    label: String,
    iconRes: Int,
    isPassword: Boolean = false
) {
    val textState = remember { androidx.compose.runtime.mutableStateOf("") } // Manage text input state
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFFF3F3F3), MaterialTheme.shapes.medium)
            .padding(horizontal = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color(0xFFFFA500),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        val visualTransformation =
            if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        val textStyle = TextStyle(fontSize = 16.sp, color = Color.Gray)
        BasicTextField(
            value = textState.value,
            onValueChange = { textState.value = it }, // Handle input change
            modifier = Modifier.fillMaxWidth(),
            textStyle = textStyle,
            visualTransformation = visualTransformation,
            decorationBox = { innerTextField ->
                if (textState.value.isEmpty()) {
                    Text(text = label, style = textStyle)
                }
                innerTextField() // Display the input field
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginPage() {
    LoginPage()
}
