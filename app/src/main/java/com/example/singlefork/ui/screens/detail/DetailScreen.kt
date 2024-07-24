package com.example.singlefork.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.singlefork.R
import com.example.singlefork.domain.model.Recipe
import com.example.singlefork.ui.screens.detail.HorizontalPagerTestScreen
import com.example.singlefork.ui.screens.detail.RecipeDetailChip

@Composable
fun DetailScreen(
    currentRecipe: Recipe, displayRecipeDetail: Boolean
) {
    Scaffold(
        topBar = {
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
//                        displayRecipeDetail = false
                    }, elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Icon(modifier = Modifier.fillMaxSize(), imageVector = Icons.Default.Clear, contentDescription = null)
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Image(
                modifier = Modifier
                    .width(400.dp)
                    .padding(bottom = 5.dp),
                alignment = Alignment.TopStart,
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = currentRecipe.name)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.AddCircle, contentDescription = null)
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = currentRecipe.prepTimeMinutes.toString())
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(0.5f), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically
                    ) {
                        RecipeDetailChip("${currentRecipe.caloriesPerServing} Calories", R.drawable.local_fire_department_24px)
                        Spacer(modifier = Modifier.width(5.dp))
                        RecipeDetailChip("${currentRecipe.rating} Rating", R.drawable.star_24px)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(0.5f), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically
                    ) {
                        RecipeDetailChip("${currentRecipe.servings} Servings", R.drawable.local_dining_24px)
                        Spacer(modifier = Modifier.width(5.dp))
                        RecipeDetailChip("${currentRecipe.reviewCount} Likes", R.drawable.favorite_24px)
                    }
                }
            }
            HorizontalPagerTestScreen(currentRecipe)
        }
        Text(modifier = Modifier.padding(paddingValues), text = ".", color = Color.Transparent
        )
    }
}

@Composable
fun DisplayCurrentRecipeDetail(currentRecipe: Recipe, displayRecipeDetail: (Unit )-> Boolean) {
    AlertDialog(
        modifier = Modifier
            .fillMaxSize(),
        onDismissRequest = {  },
        confirmButton = {
            Scaffold(
                topBar = {
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(50.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
//                                displayRecipeDetail = false
                            }, elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Icon(modifier = Modifier.fillMaxSize(), imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                },
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .width(400.dp)
                            .padding(bottom = 5.dp),
                        alignment = Alignment.TopStart,
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = currentRecipe.name)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(imageVector = Icons.Default.AddCircle, contentDescription = null)
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(text = currentRecipe.prepTimeMinutes.toString())
                            }
                        }
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(0.5f), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically
                            ) {
                                RecipeDetailChip("${currentRecipe.caloriesPerServing} Calories", R.drawable.local_fire_department_24px)
                                Spacer(modifier = Modifier.width(5.dp))
                                RecipeDetailChip("${currentRecipe.rating} Rating", R.drawable.star_24px)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(0.5f), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically
                            ) {
                                RecipeDetailChip("${currentRecipe.servings} Servings", R.drawable.local_dining_24px)
                                Spacer(modifier = Modifier.width(5.dp))
                                RecipeDetailChip("${currentRecipe.reviewCount} Likes", R.drawable.favorite_24px)
                            }
                        }
                    }
                    HorizontalPagerTestScreen(currentRecipe)
                }
                Text(modifier = Modifier.padding(paddingValues), text = ".", color = Color.Transparent
                )
            }
        }
    )
}