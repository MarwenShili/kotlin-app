package com.example.mobileexam.core.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.mobileexam.core.navigation.Screen
import com.example.mobileexam.features.customer.presentation.add_edit_customer.AddEditCustomersScreen
import com.example.mobileexam.features.customer.presentation.all_customers_screen.AllCustomersScreen
import com.example.mobileexam.features.customer.presentation.todo.TodoScreen

fun NavGraphBuilder.customerNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.CUSTOMER,
        startDestination = Screen.AllCustomersScreen.route,
    ) {
        composable(Screen.AllCustomersScreen.route) {
            AllCustomersScreen(navController = navController)
        }
        composable(Screen.TodosScreen.route) {
            TodoScreen(navController = navController)
        }
        composable(
            Screen.AddEditCustomersScreen.route + "?customerId={customerId}",
            arguments = listOf(
                navArgument(
                    name = "customerId"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
            )
        ) { entry ->
            AddEditCustomersScreen(
                navController = navController,
                customerId = entry.arguments?.getString("customerId")
            )
        }
    }
}