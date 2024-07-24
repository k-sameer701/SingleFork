package com.example.singlefork.ui.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.singlefork.R
import com.example.singlefork.domain.model.Recipe
import com.example.singlefork.ui.screens.detail.HorizontalPagerTestScreen
import com.example.singlefork.ui.screens.detail.RecipeDetailChip

@Composable
fun BreakfastRecipeCard(recipeList: List<Recipe>) {
    var displayRecipeDetail by remember { mutableStateOf(false) }
    var currentId by remember { mutableIntStateOf(0) }
    recipeList.forEach { currentRecipe ->
        if (currentRecipe.mealType.contains("Breakfast")) {
            val imageState = rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current).data(currentRecipe.image).size(
                Size.ORIGINAL).build()).state
            Card(
                modifier = Modifier.height(400.dp).width(270.dp).padding(5.dp)
                    .clickable {
                        currentId = currentRecipe.id
                        displayRecipeDetail = true
                    },
            ) {
                if (imageState is AsyncImagePainter.State.Success) {
                    Image(modifier = Modifier.width(270.dp).height(270.dp).padding(bottom = 5.dp)
                        .clickable { displayRecipeDetail = true
                            currentId = currentRecipe.id }, painter = imageState.painter, contentDescription = null, contentScale = ContentScale.Crop)
                }
                Column(
                    modifier = Modifier.padding(5.dp).height(130.dp).clickable { displayRecipeDetail = true
                        currentId = currentRecipe.id}
                ) {
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(text = currentRecipe.name, fontSize = 12.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                    Text(text = currentRecipe.prepTimeMinutes.toString(), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = currentRecipe.difficulty, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
                if (displayRecipeDetail && currentRecipe.id == currentId) {
                    AlertDialog(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(5.dp),
                        onDismissRequest = {  },
                        confirmButton = {
                            Scaffold(
                                modifier = Modifier.fillMaxSize(),
                                topBar = {
                                    Card(
                                        modifier = Modifier.padding(5.dp).size(30.dp).clip(RoundedCornerShape(8.dp))
                                            .clickable {
                                                displayRecipeDetail = false
                                            }, elevation = CardDefaults.cardElevation(8.dp)
                                    ) {
                                        Icon(modifier = Modifier.fillMaxSize(), imageVector = Icons.Default.Clear, contentDescription = null)
                                    }
                                },
                            ) { paddingValues ->
                                Column(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    if (imageState is AsyncImagePainter.State.Success) {
                                        Image(
                                            modifier = Modifier.width(270.dp).height(270.dp).padding(bottom = 5.dp).clickable { displayRecipeDetail = true }.clip(RoundedCornerShape(8.dp)), alignment = Alignment.TopStart, painter = imageState.painter, contentDescription = null, contentScale = ContentScale.Crop)
                                    }
                                    Column(
                                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                                        verticalArrangement = Arrangement.Top,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = currentRecipe.name)
                                        Column(
                                            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                                        ) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                RecipeDetailChip("${currentRecipe.caloriesPerServing} Calories", R.drawable.local_fire_department_24px)
                                                Spacer(modifier = Modifier.width(5.dp))
                                                RecipeDetailChip("${currentRecipe.rating} Ratings", R.drawable.star_24px)
                                            }
                                            Row(
                                                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                RecipeDetailChip("${currentRecipe.servings} Servings", R.drawable.local_dining_24px)
                                                Spacer(modifier = Modifier.width(5.dp))
                                                RecipeDetailChip("${currentRecipe.cookTimeMinutes} Minutes", R.drawable.schedule_24px)
                                            }
                                        }
                                        HorizontalPagerTestScreen(currentRecipe)
                                    }

                                }
                                Text(modifier = Modifier.padding(paddingValues), text = ".", color = Color.Transparent
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
fun LunchRecipeCard(recipeList: List<Recipe>) {
    var displayRecipeDetail by remember { mutableStateOf(false) }
    var currentId by remember { mutableIntStateOf(0) }
    recipeList.forEach { currentRecipe ->
        if (currentRecipe.mealType.contains("Lunch")) {
            val imageState = rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current).data(currentRecipe.image).size(
                Size.ORIGINAL).build()).state
            Card(
                modifier = Modifier.height(400.dp).width(270.dp).padding(5.dp)
                    .clickable {
                        currentId = currentRecipe.id
                        displayRecipeDetail = true
                    },
            ) {
                if (imageState is AsyncImagePainter.State.Success) {
                    Image(modifier = Modifier.width(270.dp).height(270.dp).padding(bottom = 5.dp)
                        .clickable { displayRecipeDetail = true
                            currentId = currentRecipe.id }, painter = imageState.painter, contentDescription = null, contentScale = ContentScale.Crop)
                }
                Column(
                    modifier = Modifier.padding(5.dp).height(130.dp).clickable { displayRecipeDetail = true
                        currentId = currentRecipe.id}
                ) {
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(text = currentRecipe.name, fontSize = 12.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                    Text(text = currentRecipe.prepTimeMinutes.toString(), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = currentRecipe.difficulty, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
                if (displayRecipeDetail && currentRecipe.id == currentId) {
                    AlertDialog(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(5.dp),
                        onDismissRequest = {  },
                        confirmButton = {
                            Scaffold(
                                modifier = Modifier.fillMaxSize(),
                                topBar = {
                                    Card(
                                        modifier = Modifier.padding(5.dp).size(30.dp).clip(RoundedCornerShape(8.dp))
                                            .clickable {
                                                displayRecipeDetail = false
                                            }, elevation = CardDefaults.cardElevation(8.dp)
                                    ) {
                                        Icon(modifier = Modifier.fillMaxSize(), imageVector = Icons.Default.Clear, contentDescription = null)
                                    }
                                },
                            ) { paddingValues ->
                                Column(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    if (imageState is AsyncImagePainter.State.Success) {
                                        Image(
                                            modifier = Modifier.width(270.dp).height(270.dp).padding(bottom = 5.dp).clickable { displayRecipeDetail = true }.clip(RoundedCornerShape(8.dp)), alignment = Alignment.TopStart, painter = imageState.painter, contentDescription = null, contentScale = ContentScale.Crop)
                                    }
                                    Column(
                                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                                        verticalArrangement = Arrangement.Top,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = currentRecipe.name)
                                        Column(
                                            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                                        ) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                RecipeDetailChip("${currentRecipe.caloriesPerServing} Calories", R.drawable.local_fire_department_24px)
                                                Spacer(modifier = Modifier.width(5.dp))
                                                RecipeDetailChip("${currentRecipe.rating} Ratings", R.drawable.star_24px)
                                            }
                                            Row(
                                                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                RecipeDetailChip("${currentRecipe.servings} Servings", R.drawable.local_dining_24px)
                                                Spacer(modifier = Modifier.width(5.dp))
                                                RecipeDetailChip("${currentRecipe.cookTimeMinutes} Minutes", R.drawable.schedule_24px)
                                            }
                                        }
                                        HorizontalPagerTestScreen(currentRecipe)
                                    }

                                }
                                Text(modifier = Modifier.padding(paddingValues), text = ".", color = Color.Transparent
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
fun DinnerRecipeCard(recipeList: List<Recipe>) {
    var displayRecipeDetail by remember { mutableStateOf(false) }
    var currentId by remember { mutableIntStateOf(0) }
    recipeList.forEach { currentRecipe ->
        if (currentRecipe.mealType.contains("Dinner")) {
            val imageState = rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current).data(currentRecipe.image).size(
                Size.ORIGINAL).build()).state
            Card(
                modifier = Modifier.height(400.dp).width(270.dp).padding(5.dp)
                    .clickable {
                        currentId = currentRecipe.id
                        displayRecipeDetail = true
                    },
            ) {
                if (imageState is AsyncImagePainter.State.Success) {
                    Image(modifier = Modifier.width(270.dp).height(270.dp).padding(bottom = 5.dp)
                        .clickable { displayRecipeDetail = true
                            currentId = currentRecipe.id }, painter = imageState.painter, contentDescription = null, contentScale = ContentScale.Crop)
                }
                Column(
                    modifier = Modifier.padding(5.dp).height(130.dp).clickable { displayRecipeDetail = true
                        currentId = currentRecipe.id}
                ) {
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(text = currentRecipe.name, fontSize = 12.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                    Text(text = currentRecipe.prepTimeMinutes.toString(), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = currentRecipe.difficulty, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
                if (displayRecipeDetail && currentRecipe.id == currentId) {
                    AlertDialog(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(5.dp),
                        onDismissRequest = {  },
                        confirmButton = {
                            Scaffold(
                                modifier = Modifier.fillMaxSize(),
                                topBar = {
                                    Card(
                                        modifier = Modifier.padding(5.dp).size(30.dp).clip(RoundedCornerShape(8.dp))
                                            .clickable {
                                                displayRecipeDetail = false
                                            }, elevation = CardDefaults.cardElevation(8.dp)
                                    ) {
                                        Icon(modifier = Modifier.fillMaxSize(), imageVector = Icons.Default.Clear, contentDescription = null)
                                    }
                                },
                            ) { paddingValues ->
                                Column(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    if (imageState is AsyncImagePainter.State.Success) {
                                        Image(
                                            modifier = Modifier.width(270.dp).height(270.dp).padding(bottom = 5.dp).clickable { displayRecipeDetail = true }.clip(RoundedCornerShape(8.dp)), alignment = Alignment.TopStart, painter = imageState.painter, contentDescription = null, contentScale = ContentScale.Crop)
                                    }
                                    Column(
                                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                                        verticalArrangement = Arrangement.Top,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = currentRecipe.name)
                                        Column(
                                            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                                        ) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                RecipeDetailChip("${currentRecipe.caloriesPerServing} Calories", R.drawable.local_fire_department_24px)
                                                Spacer(modifier = Modifier.width(5.dp))
                                                RecipeDetailChip("${currentRecipe.rating} Ratings", R.drawable.star_24px)
                                            }
                                            Row(
                                                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                RecipeDetailChip("${currentRecipe.servings} Servings", R.drawable.local_dining_24px)
                                                Spacer(modifier = Modifier.width(5.dp))
                                                RecipeDetailChip("${currentRecipe.cookTimeMinutes} Minutes", R.drawable.schedule_24px)
                                            }
                                        }
                                        HorizontalPagerTestScreen(currentRecipe)
                                    }

                                }
                                Text(modifier = Modifier.padding(paddingValues), text = ".", color = Color.Transparent
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
fun SearchRecipe(text: String, searchedRecipe: (String) -> Unit, placeholder: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
//            textStyle = MaterialTheme.typography.headlineSmall,
            value = text,
            onValueChange = searchedRecipe,
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            maxLines = 1  ,
            placeholder = {Text(text = placeholder)},
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )
    }
}

@Composable
fun WhatsInYourFridge() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column {
            Text(text = "What's in Your Fridge?", fontWeight = FontWeight.SemiBold)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(state = rememberScrollState())
            ) {
                CustomChip("Rice", R.drawable.rice)
                Spacer(modifier = Modifier.width(5.dp))
                CustomChip("Dosa", R.drawable.dosa)
                Spacer(modifier = Modifier.width(5.dp))
                CustomChip("Biryani", R.drawable.biryani)
                Spacer(modifier = Modifier.width(5.dp))
                CustomChip("Milkshake", R.drawable.blueberry)
                Spacer(modifier = Modifier.width(5.dp))
                CustomChip("Cocktail", R.drawable.cocktail)
                Spacer(modifier = Modifier.width(5.dp))
                CustomChip("Falafel", R.drawable.falafel)
                Spacer(modifier = Modifier.width(5.dp))
                CustomChip("Roti", R.drawable.flatbread)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(state = rememberScrollState())
            ) {
                CustomChip("Bruschetta", R.drawable.bruschetta)
                Spacer(modifier = Modifier.width(5.dp))
                CustomChip("Chicken Karahi", R.drawable.chicken_karahi)
                Spacer(modifier = Modifier.width(5.dp))
                CustomChip("Curry", R.drawable.curry)
                Spacer(modifier = Modifier.width(5.dp))
                CustomChip("Pizza", R.drawable.pizza)
                Spacer(modifier = Modifier.width(5.dp))
                CustomChip("Shrimp", R.drawable.shrimp)
                Spacer(modifier = Modifier.width(5.dp))
                CustomChip("Hotpot", R.drawable.hotpot)
                Spacer(modifier = Modifier.width(5.dp))
                CustomChip("Pasta", R.drawable.pasta)
            }
        }
    }
}

@Composable
fun CustomChip(label: String, imageIcon: Int) {
    AssistChip(
        onClick = { Log.d("Assist chip", "hello world") },
        label = { Text(text = label) },
        leadingIcon = {
            Image(modifier = Modifier.size(10.dp), painter = painterResource(id = imageIcon), contentDescription = label)
        }
    )
}