package com.example.alp_front.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.alp_front.R
import com.example.alp_front.uiStates.StringDataStatusUIState
import com.example.alp_front.uiStates.PanitiaFormUIState
import com.example.alp_front.viewmodel.PanitiaDetailViewModel
import com.example.alp_front.viewmodel.PanitiaFormViewModel
import com.example.alp_front.view.templates.CircleLoadingTemplate
import com.example.alp_front.view.templates.PanitiaDatePicker
import com.example.alp_front.view.templates.PanitiaDropdown
import com.example.alp_front.view.templates.PanitiaOutlinedTextField

@Composable
fun PanitiaFormView(
    panitiaFormViewModel: PanitiaFormViewModel, // Changed from PanitiaFormViewModel to panitiaFormViewModel
    modifier: Modifier = Modifier,
    context: Context,
    navController: NavHostController,
    token: String,
    panitiaDetailViewModel: PanitiaDetailViewModel
) {
    val panitiaFormUIState = panitiaFormViewModel.panitiaFormUIState.collectAsState() // Fixed to match the correct variable name
    val submissionStatus = panitiaFormViewModel.submissionStatus

    LaunchedEffect(panitiaFormViewModel.submissionStatus) {
        val dataStatus = panitiaFormViewModel.submissionStatus
        if (dataStatus is StringDataStatusUIState.Failed) {
            Toast.makeText(context, dataStatus.errorMessage, Toast.LENGTH_SHORT).show()
            panitiaFormViewModel.clearErrorMessage()
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Panitia Form", // Consider changing to "Panitia Form"
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 15.dp)
            )

            // Using panitiaFormViewModel for input handling
            PanitiaOutlinedTextField(
                inputValue = panitiaFormViewModel.titleInput, // Fixed reference
                onValueChange = {
                    panitiaFormViewModel.changeTitleInput(it) // Fixed reference
                    panitiaFormViewModel.checkNullFormValues() // Fixed reference
                },
                modifier = Modifier
                    .fillMaxWidth(),
                labelText = stringResource(R.string.title_text),
                placeholderText = stringResource(R.string.title_text),
                minLine = 1,
                maxLine = 1
            )

            PanitiaOutlinedTextField(
                inputValue = panitiaFormViewModel.organisasiInput, // Fixed reference
                onValueChange = {
                    panitiaFormViewModel.changeOrganisasiInput(it) // Fixed reference
                    panitiaFormViewModel.checkNullFormValues() // Fixed reference
                },
                modifier = Modifier
                    .fillMaxWidth(),
                labelText = stringResource(R.string.organisasi_text),
                placeholderText = stringResource(R.string.organisasi_text),
                minLine = 1,
                maxLine = 1
            )

            PanitiaOutlinedTextField(
                inputValue = panitiaFormViewModel.descriptionInput, // Fixed reference
                onValueChange = {
                    panitiaFormViewModel.changeDescriptionInput(it) // Fixed reference
                    panitiaFormViewModel.checkNullFormValues() // Fixed reference
                },
                labelText = stringResource(R.string.description_text),
                placeholderText = stringResource(R.string.description_text),
                minLine = 10,
                maxLine = 10,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )

            PanitiaOutlinedTextField(
                inputValue = panitiaFormViewModel.posterInput, // Fixed reference
                onValueChange = {
                    panitiaFormViewModel.changePosterInput(it) // Fixed reference
                    panitiaFormViewModel.checkNullFormValues() // Fixed reference
                },
                modifier = Modifier
                    .fillMaxWidth(),
                labelText = stringResource(R.string.poster_text),
                placeholderText = stringResource(R.string.poster_text),
                minLine = 1,
                maxLine = 1
            )

            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
            ) {


            }

            // TODO: Date Picker
            PanitiaDatePicker(
                datePickerValue = panitiaFormViewModel.startDateInput, // Fixed reference
                showCalendarDialog = {
                    panitiaFormViewModel.showDatePickerDialog(panitiaFormViewModel.initDatePickerDialog(context)) // Fixed reference
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    panitiaFormViewModel.resetViewModel() // Fixed reference
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Gray)
            ) {
                Text(text = stringResource(R.string.cancel_text))
            }

            when(submissionStatus) {
                is StringDataStatusUIState.Loading -> CircleLoadingTemplate(
                    color = Color.Blue,
                    trackColor = Color.Transparent,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
                else -> Button(
                    onClick = {
                        if (panitiaFormViewModel.isUpdate) {
                            panitiaFormViewModel.updatePanitia(token, getPanitia =  {
                                panitiaDetailViewModel.getPanitia(token, panitiaFormViewModel.id, navController, panitiaFormViewModel.isUpdate) // Fixed reference
                            })
                        } else {
                            panitiaFormViewModel.createPanitia(navController, token = token) // Fixed reference
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    enabled = panitiaFormUIState.value.saveButtonEnabled, // Fixed reference
                    colors = ButtonDefaults.buttonColors(panitiaFormViewModel.changeSaveButtonColor()) // Fixed reference
                ) {
                    Text(text = stringResource(R.string.save_text))
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun CreatePanitiaPreview() {
    PanitiaFormView(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .padding(top = 8.dp),
        context = LocalContext.current,
        navController = rememberNavController(),
        panitiaFormViewModel = viewModel(factory = PanitiaFormViewModel.Factory), // Fixed reference
        token = "",
        panitiaDetailViewModel = viewModel(factory = PanitiaDetailViewModel.Factory) // Fixed reference
    )
}
