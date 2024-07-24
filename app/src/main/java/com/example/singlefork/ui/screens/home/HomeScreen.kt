package com.example.singlefork.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.singlefork.R
import com.example.singlefork.domain.model.Recipe
import com.example.singlefork.ui.RecipeViewModel
import com.example.singlefork.ui.navigation.RecipeScreens

@Composable
fun HomeScreen(viewModel: RecipeViewModel, navHostController: NavHostController) {
    var searchedRecipe by remember { mutableStateOf("") }
    val recipeList = viewModel.recipes.collectAsState().value
    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier.wrapContentHeight().clip(RoundedCornerShape(10.dp))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(0.3f), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(modifier = Modifier.clickable { navHostController.navigate(
                            RecipeScreens.HOMESCREEN.name) }, imageVector = Icons.Default.Home, contentDescription = null)
                        Text(text = "Home")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(modifier = Modifier.clickable { navHostController.navigate(
                            RecipeScreens.SEARCHSCREEN.name) }, imageVector = Icons.Default.Search, contentDescription = null)
                        Icon(modifier = Modifier.clickable { navHostController.navigate(
                            RecipeScreens.HOMESCREEN.name) }, imageVector = Icons.Default.Add, contentDescription = null)
                        Icon(modifier = Modifier.clickable { navHostController.navigate(
                            RecipeScreens.TODAYSCREEN.name) }, imageVector = Icons.Default.Menu, contentDescription = null)
                    }
                }
            }
        }
    ) { paddingValues ->
        Text(modifier = Modifier.padding(paddingValues), text = ".", color = Color.Transparent)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            HelloChef()
            Spacer(modifier = Modifier.height(5.dp))
            SearchRecipe(text = searchedRecipe, searchedRecipe = {searchedRecipe = it}, placeholder = "Type ingredients")
            WhatsInYourFridge()
            BreakFastRecipe(recipeList)
            Spacer(modifier = Modifier.height(8.dp))
            LunchRecipe(recipeList)
            Spacer(modifier = Modifier.height(8.dp))
            DinerRecipe(recipeList)
            Spacer(modifier = Modifier.height(90.dp))
            Text(text = ".")
        }
    }
}

@Composable
fun HelloChef() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(5.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(text = "Hello, Chef", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "What do you want to cook today?", fontWeight = FontWeight.SemiBold, maxLines = 2)
        }
        Image(
            modifier = Modifier.size(70.dp), painter = painterResource(id = R.drawable.cooking), contentDescription = null
        )
    }
}

@Composable
fun BreakFastRecipe(recipeList: List<Recipe>) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Recipes for breakfast")
                Text(text = "see all")
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
            ) {
                BreakfastRecipeCard(recipeList)
            }
        }
    }
}

@Composable
fun LunchRecipe(recipeList: List<Recipe>) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Recipes for lunch")
                Text(text = "see all")
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                LunchRecipeCard(recipeList)
            }
        }
    }
}

@Composable
fun DinerRecipe(recipeList: List<Recipe>) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Recipes for dinner")
                Text(text = "see all")
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                DinnerRecipeCard(recipeList)
            }
        }
    }
}
