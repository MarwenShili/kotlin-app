package com.example.mobileexam.core.navigation

sealed class Screen(val route: String) {
    object AllCustomersScreen : Screen("all_customers_screen")
    object AddEditCustomersScreen : Screen("add_edit_customers_screen")
    object TodosScreen : Screen("todos_screen")
}