package com.example.singlefork.ui.screens.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.singlefork.domain.model.Recipe
import kotlinx.coroutines.launch

@Composable
fun RecipeDetailChip(recipeDetail: String, img: Int) {
    Surface(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(5.dp),
                color = Color.LightGray
            ) {
                Icon(painter = painterResource(id = img), contentDescription = null)
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = recipeDetail,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun ShowIngredients(currentRecipe: Recipe) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        currentRecipe.ingredients.forEach { currentRecipeIngredients ->
            Text(
                text = "- $currentRecipeIngredients",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun ShowInstructions(currentRecipe: Recipe) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        currentRecipe.instructions.forEach { currentRecipeInstruction ->
            Text(
                text = "- $currentRecipeInstruction",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun HorizontalPagerTestScreen(currentRecipe: Recipe) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState, modifier = Modifier.align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), contentAlignment = Alignment.Center
            ) {
                if (it == 0) {
                    ShowInstructions(currentRecipe)
                } else {
                    ShowIngredients(currentRecipe)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier.clip(
                    RoundedCornerShape(
                        bottomStart = 10.dp,
                        topStart = 10.dp
                    )
                )
            ) {
                Row(
                    modifier = Modifier
                        .width(125.dp)
                        .height(45.dp)
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .width(125.dp)
                            .height(30.dp),
                        onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) } },
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Follow", style = MaterialTheme.typography.headlineSmall)
                        }
                    }
                }
            }
            Surface(
                modifier = Modifier.clip(
                    RoundedCornerShape(
                        bottomEnd = 10.dp,
                        topEnd = 10.dp
                    )
                )
            ) {
                Row(
                    modifier = Modifier
                        .width(125.dp)
                        .height(45.dp)
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .width(125.dp)
                            .height(30.dp),
                        onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } },
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Items", style = MaterialTheme.typography.headlineSmall)
                        }
                    }
                }
            }
        }
    }
}