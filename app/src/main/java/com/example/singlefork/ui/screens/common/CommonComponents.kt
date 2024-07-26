package com.example.singlefork.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.singlefork.R
import com.example.singlefork.domain.model.Recipe

@Composable
fun CustomRecipe(recipeList: List<Recipe>, mealType: String, category: String) {
    Surface(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                    text = category,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                    text = "see all",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(
                modifier = Modifier
                    .height(5.dp)
                    .background(MaterialTheme.colorScheme.surface)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .horizontalScroll(rememberScrollState())
            ) {
                CustomRecipeCard(recipeList, mealType)
            }
        }
    }
}

@Composable
fun CustomRecipeCard(recipeList: List<Recipe>, mealType: String) {
    var displayRecipeDetail by remember { mutableStateOf(false) }
    var currentId by remember { mutableIntStateOf(0) }
    recipeList.forEach { currentRecipe ->
        if (currentRecipe.mealType.contains(mealType)) {
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
                colors = CardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    disabledContainerColor = MaterialTheme.colorScheme.background,
                    disabledContentColor = MaterialTheme.colorScheme.onBackground
                ),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                if (imageState is AsyncImagePainter.State.Success) {
                    Image(
                        modifier = Modifier
                            .width(270.dp)
                            .height(270.dp)
                            .background(MaterialTheme.colorScheme.background)
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
                        .padding(5.dp)
                        .wrapContentHeight()
                        .background(MaterialTheme.colorScheme.background)
                        .clickable {
                            displayRecipeDetail = true
                            currentId = currentRecipe.id
                        }
                ) {
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.background)
                    )
                    Text(
                        modifier = Modifier.background(MaterialTheme.colorScheme.background),
                        text = currentRecipe.name,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(
                        modifier = Modifier
                            .height(3.dp)
                            .background(MaterialTheme.colorScheme.background)
                    )
                    Row {
                        Icon(
                            modifier = Modifier.background(MaterialTheme.colorScheme.background),
                            painter = painterResource(id = R.drawable.schedule_24px),
                            contentDescription = "Time",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(
                            modifier = Modifier
                                .width(3.dp)
                                .background(MaterialTheme.colorScheme.background)
                        )
                        Text(
                            modifier = Modifier.background(MaterialTheme.colorScheme.background),
                            text = "${currentRecipe.prepTimeMinutes} Minutes",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onBackground
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
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                        shape = RoundedCornerShape(8.dp),
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
                                            },
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
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
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
                                            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                                            .verticalScroll(
                                                rememberScrollState()
                                            ),
                                        verticalArrangement = Arrangement.Top,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = currentRecipe.name,
                                            style = MaterialTheme.typography.headlineMedium,
                                        )
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(MaterialTheme.colorScheme.surfaceContainerHighest),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .background(MaterialTheme.colorScheme.surfaceContainerHighest),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                RecipeDetailChip(
                                                    "${currentRecipe.caloriesPerServing} Calories",
                                                    R.drawable.local_fire_department_24px
                                                )
                                                Spacer(
                                                    modifier = Modifier
                                                        .width(5.dp)
                                                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                                                )
                                                RecipeDetailChip(
                                                    "${currentRecipe.rating}",
                                                    R.drawable.star_24px
                                                )
                                            }
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .background(MaterialTheme.colorScheme.surfaceContainerHighest),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                RecipeDetailChip(
                                                    "${currentRecipe.servings} Servings",
                                                    R.drawable.local_dining_24px
                                                )
                                                Spacer(
                                                    modifier = Modifier
                                                        .width(5.dp)
                                                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                                                )
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
                        },
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
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = MaterialTheme.typography.labelLarge,
            value = text,
            onValueChange = searchedRecipe,
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            maxLines = 1,
            placeholder = { Text(text = placeholder) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(8.dp)
        )
    }
}