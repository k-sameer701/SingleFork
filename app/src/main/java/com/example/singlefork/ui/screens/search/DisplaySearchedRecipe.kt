package com.example.singlefork.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.singlefork.R
import com.example.singlefork.domain.model.Recipe
import com.example.singlefork.ui.screens.common.HorizontalPagerTestScreen
import com.example.singlefork.ui.screens.common.RecipeDetailChip

@Composable
fun DisplayCustomRecipeCard(recipeList: List<Recipe>, mealType: String) {
    var displayRecipeDetail by remember { mutableStateOf(false) }
    var currentId by remember { mutableIntStateOf(0) }

    val filteredNotes = recipeList.filter { currentRecipe ->
        (currentRecipe.name.contains(
            mealType,
            ignoreCase = true
        ) || currentRecipe.ingredients.contains(mealType)
                || currentRecipe.cuisine.contains(
            mealType,
            ignoreCase = true
        ) || currentRecipe.tags.contains(mealType))
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        filteredNotes.forEach { currentRecipe ->
            val imageState = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current).data(currentRecipe.image).size(
                    Size.ORIGINAL
                ).build()
            ).state
            Card(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(270.dp)
                    .padding(5.dp)
                    .clickable {
                        currentId = currentRecipe.id
                        displayRecipeDetail = true
                    },

                ) {
                if (imageState is AsyncImagePainter.State.Success) {
                    Image(
                        modifier = Modifier
                            .width(270.dp)
                            .height(270.dp)
                            .padding(bottom = 5.dp)
                            .clickable {
                                displayRecipeDetail = true
                                currentId = currentRecipe.id
                            },
                        painter = imageState.painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(5.dp)
                        .clickable {
                            displayRecipeDetail = true
                            currentId = currentRecipe.id
                        }
                ) {
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(
                        text = currentRecipe.name,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.schedule_24px),
                            contentDescription = "Time"
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "${currentRecipe.prepTimeMinutes} Minutes",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
                if (displayRecipeDetail && currentRecipe.id == currentId) {
                    AlertDialog(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(5.dp),
                        onDismissRequest = { displayRecipeDetail = !displayRecipeDetail },
                        confirmButton = {
                            Scaffold(
                                modifier = Modifier.fillMaxSize(),
                                topBar = {
                                    Card(
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .size(30.dp)
                                            .clip(
                                                RoundedCornerShape(8.dp)
                                            )
                                            .clickable {
                                                displayRecipeDetail = false
                                            }, elevation = CardDefaults.cardElevation(8.dp)
                                    ) {
                                        Icon(
                                            modifier = Modifier.fillMaxSize(),
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = null
                                        )
                                    }
                                },
                            ) { paddingValues ->
                                Column(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    if (imageState is AsyncImagePainter.State.Success) {
                                        Image(
                                            modifier = Modifier
                                                .width(270.dp)
                                                .height(270.dp)
                                                .padding(bottom = 5.dp)
                                                .clickable { displayRecipeDetail = true }
                                                .clip(
                                                    RoundedCornerShape(8.dp)
                                                ),
                                            alignment = Alignment.TopStart,
                                            painter = imageState.painter,
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .verticalScroll(rememberScrollState()),
                                        verticalArrangement = Arrangement.Top,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = currentRecipe.name)
                                        Column(
                                            modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                RecipeDetailChip(
                                                    "${currentRecipe.caloriesPerServing} Calories",
                                                    R.drawable.local_fire_department_24px
                                                )
                                                Spacer(modifier = Modifier.width(5.dp))
                                                RecipeDetailChip(
                                                    "${currentRecipe.rating}",
                                                    R.drawable.star_24px
                                                )
                                            }
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                RecipeDetailChip(
                                                    "${currentRecipe.servings} Servings",
                                                    R.drawable.local_dining_24px
                                                )
                                                Spacer(modifier = Modifier.width(5.dp))
                                                RecipeDetailChip(
                                                    " ${currentRecipe.cookTimeMinutes}",
                                                    R.drawable.schedule_24px
                                                )
                                            }
                                        }
                                        HorizontalPagerTestScreen(currentRecipe)
                                    }
                                }
                                Text(
                                    modifier = Modifier.padding(paddingValues),
                                    text = ".",
                                    color = Color.Transparent
                                )
                            }
                        }
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.width(5.dp))

}

@Composable
fun ChefSearch() {
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
                text = "Discover New Flavors",
                maxLines = 2,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.cocktail),
            contentDescription = null
        )
    }
}