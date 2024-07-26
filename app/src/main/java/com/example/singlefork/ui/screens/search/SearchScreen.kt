package com.example.singlefork.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.singlefork.R
import com.example.singlefork.ui.RecipeViewModel
import com.example.singlefork.ui.navigation.RecipeScreens
import com.example.singlefork.ui.screens.common.SearchRecipe

@Composable
fun SearchScreen(viewModel: RecipeViewModel, navHostController: NavHostController) {
    var searchedRecipe by remember { mutableStateOf("") }
    val recipeList = viewModel.recipes.collectAsState().value
    Scaffold(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(10.dp)),
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .fillMaxWidth(0.3f)
                            .clickable {
                                navHostController.navigate(RecipeScreens.HOMESCREEN.name) {
                                    popUpTo(0)
                                }
                            },
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer),
                            painter = painterResource(R.drawable.home_24px),
                            contentDescription = "Home",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer),
                            text = "Home",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                    Row(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .fillMaxWidth(0.9f),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                .clickable {
                                    navHostController.navigate(
                                        RecipeScreens.SEARCHSCREEN.name
                                    ) { popUpTo(0) }
                                },
                            painter = painterResource(R.drawable.search_24px),
                            contentDescription = "Search Screen",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Icon(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                .clickable {
                                    navHostController.navigate(
                                        RecipeScreens.CATEGORYSCREEN.name
                                    ) { popUpTo(0) }
                                },
                            painter = painterResource(id = R.drawable.restaurant_menu_24px),
                            contentDescription = "Category",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Icon(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                .clickable {
                                    navHostController.navigate(
                                        RecipeScreens.TODAYSCREEN.name
                                    ) { popUpTo(0) }
                                },
                            painter = painterResource(id = R.drawable.calendar_today_24px),
                            contentDescription = "Today's Dish",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxSize()
                .padding(top = 18.dp, bottom = 5.dp, start = 5.dp, end = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ChefSearch()
            Spacer(modifier = Modifier.height(5.dp))
            SearchRecipe(
                text = searchedRecipe,
                searchedRecipe = { searchedRecipe = it },
                placeholder = "Type ingredients"
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                DisplayCustomRecipeCard(recipeList, searchedRecipe)
            }
        }
        Text(
            modifier = Modifier.padding(paddingValues), text = ".", color = Color.Transparent
        )
    }
}