package com.example.mobileexam.features.customer.presentation.all_customers_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobileexam.features.customer.presentation.components.CustomSearch
import com.example.mobileexam.R
import com.example.mobileexam.core.navigation.Screen
import com.example.mobileexam.features.customer.presentation.components.CustomerCard
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AllCustomersScreen(navController: NavController) {
    val viewModel = getViewModel<AllCustomersViewModel>()
    val state = viewModel.state.value
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { viewModel.onEvent(AllCustomersEvent.GetAllUsers) }
    )

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White, darkIcons = true
        )
        systemUiController.setNavigationBarColor(
            color = Color.White, darkIcons = true
        )
    }

    LaunchedEffect(key1 = context) {
        val updateData =
            navController.currentBackStackEntry?.savedStateHandle?.get<Boolean>("updateData")
                ?: false

        if (updateData) {
            viewModel.onEvent(AllCustomersEvent.GetAllUsers)
        }
    }

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures {
                focusManager.clearFocus()
            }
        }
        .safeContentPadding()
        .padding(horizontal = 20.dp),
        contentColor = Color.White,
        floatingActionButton = {
            Button(
                onClick = {
                    navController.navigate(Screen.AddEditCustomersScreen.route)
                },
                modifier = Modifier
                    .width(320.dp)
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )

            ) {
                Text(
                    text = "Add Student",
                    color = Color.White
                )
            }
        }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Students",
                    modifier = Modifier
                        .padding(top = 20.dp),
                    style = TextStyle(
                        fontSize = 36.sp,
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF2A2B30),
                    )
                )

            }
            CustomSearch(
                modifier = Modifier.padding(vertical = 20.dp),
                state = state.query,
                onTextChanged = {
                    viewModel.onEvent(AllCustomersEvent.Search(it))
                },
                focusManager = focusManager
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(state.filteredUsers.count()) { index ->
                        CustomerCard(
                            modifier = Modifier.padding(vertical = 8.dp),
                            customer = state.filteredUsers[index],
                            onDeleteClick = {
                                viewModel.onEvent(AllCustomersEvent.DeleteUser(state.filteredUsers[index].id.toString()))
                            },
                            onEditClick = {
                                navController.navigate(Screen.AddEditCustomersScreen.route + "?customerId=${state.filteredUsers[index].id}")
                            }
                        )
                    }
                }

                PullRefreshIndicator(
                    refreshing = viewModel.state.value.isLoading,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                    backgroundColor = Color(0xFFF6F6F9),
                )
            }
        }
    }
}
