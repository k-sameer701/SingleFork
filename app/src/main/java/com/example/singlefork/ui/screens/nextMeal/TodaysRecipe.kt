package com.example.singlefork.ui.screens.nextMeal

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.singlefork.R
import com.example.singlefork.ui.RecipeViewModel
import com.example.singlefork.ui.navigation.RecipeScreens
import com.example.singlefork.ui.screens.detail.HorizontalPagerTestScreen
import com.example.singlefork.ui.screens.detail.RecipeDetailChip

@Composable
fun TodayRecipeScreen(viewModel: RecipeViewModel, navHostController: NavHostController) {
    val randomId = (0..30).random()
    val todayRecipe = viewModel.recipes.collectAsState().value[randomId]
    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
                        modifier = Modifier.fillMaxWidth(0.3f),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.clickable {
                                navHostController.navigate(
                                    RecipeScreens.HOMESCREEN.name
                                )
                            },
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
                                )
                            },
                            painter = painterResource(R.drawable.search_24px),
                            contentDescription = "Search Screen"
                        )
                        Icon(
                            modifier = Modifier.clickable {
                                navHostController.navigate(
                                    RecipeScreens.CATEGORYSCREEN.name
                                )
                            },
                            painter = painterResource(id = R.drawable.restaurant_menu_24px),
                            contentDescription = "Category"
                        )
                        Icon(
                            modifier = Modifier.clickable {
                                navHostController.navigate(
                                    RecipeScreens.TODAYSCREEN.name
                                )
                            },
                            painter = painterResource(id = R.drawable.calendar_today_24px),
                            contentDescription = "Today's Dish"
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
            modifier = Modifier.fillMaxSize()
        ) {
            if (imageState is AsyncImagePainter.State.Success) {
                Image(
                    modifier = Modifier
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
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = todayRecipe.name)
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
                            "${todayRecipe.caloriesPerServing} Calories",
                            R.drawable.local_fire_department_24px
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        RecipeDetailChip("${todayRecipe.rating} Ratings", R.drawable.star_24px)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RecipeDetailChip(
                            "${todayRecipe.servings} Servings",
                            R.drawable.local_dining_24px
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        RecipeDetailChip(
                            "${todayRecipe.cookTimeMinutes} Minutes",
                            R.drawable.schedule_24px
                        )
                    }
                }
                HorizontalPagerTestScreen(todayRecipe)
            }
        }
        Text(
            modifier = Modifier.padding(paddingValues), text = ".", color = Color.Transparent
        )
    }
}