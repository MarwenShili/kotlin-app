package com.example.mobileexam.features.customer.presentation.add_edit_customer

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobileexam.R
import com.example.mobileexam.features.customer.presentation.components.CustomInput
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditCustomersScreen(navController: NavController, customerId: String?) {
    val viewModel = getViewModel<AddEditCustomersViewModel>()
    val state = viewModel.state.value
    val focusManager = LocalFocusManager.current
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
        systemUiController.setNavigationBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    LaunchedEffect(key1 = true) {
        if (!customerId.isNullOrBlank()) {
            viewModel.onEvent(AddEditCustomersEvent.GetCustomerById(customerId))
        }
    }

    LaunchedEffect(key1 = state.success) {
        if (state.success) {
            navController.previousBackStackEntry?.savedStateHandle?.set<Boolean>(
                "updateData",
                true
            )
            navController.popBackStack()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }
            .safeContentPadding()
            .padding(horizontal = 20.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color(0xFF2A2B30)
                )
            }
            Text(
                text = if (customerId.isNullOrBlank()) "Add Student" else "Edit Student",
                modifier = Modifier
                    .padding(bottom = 10.dp),
                style = TextStyle(
                    fontSize = 36.sp,
                    fontFamily = FontFamily(Font(R.font.inter_bold)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF2A2B30),
                )
            )

            Text(
                text = "Name",
                modifier = Modifier
                    .padding(bottom = 5.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF2A2B30),
                )
            )
            CustomInput(
                onTextChanged = {
                    viewModel.onEvent(AddEditCustomersEvent.OnNameChange(it))
                },
                focusManager = focusManager,
                currentStateError = "",
                placeholder = "name",
                text = state.name,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Last Name",
                modifier = Modifier
                    .padding(bottom = 5.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF2A2B30),
                )
            )
            CustomInput(
                onTextChanged = {
                    viewModel.onEvent(AddEditCustomersEvent.OnLastNameChange(it))
                },
                focusManager = focusManager,
                currentStateError = "",
                placeholder = "last name",
                text = state.lastName,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Email",
                modifier = Modifier
                    .padding(bottom = 5.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF2A2B30),
                )
            )
            CustomInput(
                onTextChanged = {
                    viewModel.onEvent(AddEditCustomersEvent.OnEmailChange(it))
                },
                focusManager = focusManager,
                currentStateError = "",
                placeholder = "email",
                text = state.email,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Phone Number",
                modifier = Modifier
                    .padding(bottom = 5.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF2A2B30),
                )
            )
            CustomInput(
                onTextChanged = {
                    viewModel.onEvent(AddEditCustomersEvent.OnPhoneNumberChange(it))
                },
                focusManager = focusManager,
                currentStateError = "",
                placeholder = "phone number",
                text = state.phoneNumber,
            )

            Spacer(modifier = Modifier.height(80.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    if (customerId.isNullOrBlank())
                        viewModel.onEvent(AddEditCustomersEvent.AddCustomer)
                    else
                        viewModel.onEvent(AddEditCustomersEvent.EditCustomer(customerId))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text(
                    text = if (customerId.isNullOrBlank()) "Add" else "Update",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.inter_semi_bold)),
                        fontWeight = FontWeight(600),
                        color = Color.White,
                    )
                )
            }
        }
    }
}