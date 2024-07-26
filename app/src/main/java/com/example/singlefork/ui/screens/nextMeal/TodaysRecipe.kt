package com.example.singlefork.ui.screens.nextMeal

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.singlefork.R
import com.example.singlefork.ui.RecipeViewModel
import com.example.singlefork.ui.navigation.RecipeScreens
import com.example.singlefork.ui.screens.common.HorizontalPagerTestScreen
import com.example.singlefork.ui.screens.common.RecipeDetailChip

@Composable
fun TodayRecipeScreen(viewModel: RecipeViewModel, navHostController: NavHostController) {
    val randomId = (0..30).random()
    val todayRecipe = viewModel.recipes.collectAsState().value[randomId]
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
        val imageState = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current).data(todayRecipe.image).size(
                Size.ORIGINAL
            ).build()
        ).state
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                .padding(top = 20.dp, bottom = 5.dp, start = 5.dp, end = 5.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .height(8.dp)
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainerHighest),
                    text = "Today's Spotlight",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                    .height(8.dp)
            )
            if (imageState is AsyncImagePainter.State.Success) {
                Image(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                        .width(270.dp)
                        .height(270.dp)
                        .padding(bottom = 5.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(8.dp)),
                    alignment = Alignment.TopStart,
                    painter = imageState.painter,
                    contentDescription = "Random Dish Image",
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainerHighest),
                    text = todayRecipe.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RecipeDetailChip(
                            "${todayRecipe.caloriesPerServing} Calories",
                            R.drawable.local_fire_department_24px
                        )
                        Spacer(
                            modifier = Modifier
                                .width(5.dp)
                                .background(MaterialTheme.colorScheme.background)
                        )
                        RecipeDetailChip("${todayRecipe.rating} Ratings", R.drawable.star_24px)
                    }
                    Row(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RecipeDetailChip(
                            "${todayRecipe.servings} Servings",
                            R.drawable.local_dining_24px
                        )
                        Spacer(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                                .width(5.dp)
                        )
                        RecipeDetailChip(
                            "${todayRecipe.prepTimeMinutes} Minutes",
                            R.drawable.schedule_24px
                        )
                    }
                }
                HorizontalPagerTestScreen(todayRecipe)
                Spacer(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                        .height(50.dp)
                )
                Text(text = ".", color = Color.Transparent)
            }
        }
        Text(
            modifier = Modifier.padding(paddingValues), text = ".", color = Color.Transparent
        )
    }
}