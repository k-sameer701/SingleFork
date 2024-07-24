package com.example.singlefork.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.singlefork.ui.RecipeViewModel
import com.example.singlefork.ui.screens.category.CategoryScreen
import com.example.singlefork.ui.screens.home.HomeScreen
import com.example.singlefork.ui.screens.search.SearchScreen
import com.example.singlefork.ui.screens.nextMeal.TodayRecipeScreen

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    val viewModel: RecipeViewModel = viewModel(factory = RecipeViewModel.factory)
    NavHost(navController = navController, startDestination = RecipeScreens.HOMESCREEN.name) {
        composable(route = RecipeScreens.HOMESCREEN.name) {
            HomeScreen(viewModel = viewModel, navHostController = navController)
        }
        composable(route = RecipeScreens.SEARCHSCREEN.name) {
            SearchScreen(viewModel = viewModel, navHostController = navController)
        }
        composable(route = RecipeScreens.TODAYSCREEN.name) {
            TodayRecipeScreen(viewModel = viewModel, navHostController = navController)
        }
        composable(route = RecipeScreens.CATEGORYSCREEN.name) {
            CategoryScreen(viewModel = viewModel, navHostController = navController)
        }
    }
}