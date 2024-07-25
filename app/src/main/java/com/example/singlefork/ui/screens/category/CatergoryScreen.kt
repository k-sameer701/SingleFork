package com.example.singlefork.ui.screens.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.singlefork.R
import com.example.singlefork.ui.RecipeViewModel
import com.example.singlefork.ui.navigation.RecipeScreens
import com.example.singlefork.ui.screens.common.CustomRecipe

@Composable
fun CategoryScreen(viewModel: RecipeViewModel, navHostController: NavHostController) {
    val recipeList = viewModel.recipes.collectAsState().value
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp),
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(10.dp))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .clickable {
                                navHostController.navigate(
                                    RecipeScreens.HOMESCREEN.name
                                ) { popUpTo(0) }
                            },
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.home_24px),
                            contentDescription = "Home"
                        )
                        Text(text = "Home")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.clickable {
                                navHostController.navigate(
                                    RecipeScreens.SEARCHSCREEN.name
                                ) { popUpTo(0) }
                            },
                            painter = painterResource(R.drawable.search_24px),
                            contentDescription = "Search Screen"
                        )
                        Icon(
                            modifier = Modifier.clickable {
                                navHostController.navigate(
                                    RecipeScreens.CATEGORYSCREEN.name
                                ) { popUpTo(0) }
                            },
                            painter = painterResource(id = R.drawable.restaurant_menu_24px),
                            contentDescription = "Category"
                        )
                        Icon(
                            modifier = Modifier.clickable {
                                navHostController.navigate(
                                    RecipeScreens.TODAYSCREEN.name
                                ) { popUpTo(0) }
                            },
                            painter = painterResource(id = R.drawable.calendar_today_24px),
                            contentDescription = "Today's Dish"
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ChefCategory()
            CustomRecipe(recipeList = recipeList, mealType = "Breakfast", category = "Trending Now")
            Spacer(modifier = Modifier.height(8.dp))
            CustomRecipe(recipeList = recipeList, mealType = "Lunch", category = "Popular Cuisine")
            Spacer(modifier = Modifier.height(8.dp))
            CustomRecipe(recipeList = recipeList, mealType = "Dinner", category = "Healthy Pick")
            Spacer(modifier = Modifier.height(8.dp))
            CustomRecipe(recipeList = recipeList, mealType = "Snacks", category = "Quick and Easy")
            Spacer(modifier = Modifier.height(8.dp))
            CustomRecipe(
                recipeList = recipeList,
                mealType = "Beverage",
                category = "Light and Healthy"
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomRecipe(
                recipeList = recipeList,
                mealType = "Dessert",
                category = "Desserts and Treats"
            )
            Spacer(modifier = Modifier.height(90.dp))
            Text(text = ".")
        }
        Text(
            modifier = Modifier.padding(paddingValues), text = ".", color = Color.Transparent
        )
    }
}

@Composable
fun ChefCategory() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Cook Something New Today",
                maxLines = 2,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
        }
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.spaguetti),
            contentDescription = null
        )
    }
}