package com.mdanielelel.danielfrontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mdanielelel.danielfrontend.R
import com.mdanielelel.danielfrontend.ui.theme.DanielFrontEndTheme

@Composable
fun InputFieldComponent(
    label: String,
    icon: @Composable () -> Unit,
    value: String,
    onValueChange: (String) -> Unit,
    obscureText: Boolean = false,
    modifier: Modifier = Modifier
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val isObscureText = obscureText && !isPasswordVisible

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = Color(0xFFFF7043),
                        shape = RoundedCornerShape(12.dp)
                    ),
            ) {
                icon()
            }

            Spacer(modifier = Modifier.size(12.dp))

            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.size(12.dp))

        val containerColor = Color(0xFFEFEFEF)

        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            maxLines = 1,
            visualTransformation = if (isObscureText) PasswordVisualTransformation() else VisualTransformation.None,
            textStyle = MaterialTheme.typography.bodyMedium,
            trailingIcon = if (obscureText) {
                {
                    IconButton(
                        onClick = { isPasswordVisible = !isPasswordVisible }
                    ) {
                        SvgIconComposable(
                            resourceId = if (isPasswordVisible)
                                R.raw.ic_eye_on
                            else
                                R.raw.ic_eye_off,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            } else null,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InputFieldPreview() {
    DanielFrontEndTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            InputFieldComponent(
                label = "Email",
                icon = { SvgIconComposable(resourceId = R.raw.ic_email) },
                value = "",
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputFieldComponent(
                label = "Password",
                icon = { SvgIconComposable(resourceId = R.raw.ic_lock) },
                value = "password123",
                onValueChange = {},
                obscureText = true
            )
        }
    }
}