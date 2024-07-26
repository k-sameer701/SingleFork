package com.example.singlefork.ui.screens.common

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.singlefork.domain.model.Recipe
import kotlinx.coroutines.launch

@Composable
fun RecipeDetailChip(recipeDetail: String, img: Int) {
    Surface(
        modifier = Modifier,
        color = MaterialTheme.colorScheme.surfaceContainerHighest
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                .padding(4.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier,
                shape = RoundedCornerShape(5.dp),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shadowElevation = 2.dp
            ) {
                Icon(
                    modifier = Modifier.background(MaterialTheme.colorScheme.tertiaryContainer),
                    painter = painterResource(id = img),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
            Spacer(
                modifier = Modifier
                    .width(5.dp)
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            )
            Text(
                text = recipeDetail,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
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
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
            .fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                .align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest)
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
                .background(MaterialTheme.colorScheme.surfaceContainerHighest)
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
                ),
                color = MaterialTheme.colorScheme.surfaceContainerHighest
            ) {
                Row(
                    modifier = Modifier
                        .width(125.dp)
                        .height(45.dp)
                        .padding(4.dp)
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .width(125.dp)
                            .height(40.dp),
                        onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) } },
                        shape = RoundedCornerShape(5.dp),
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        shadowElevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.tertiaryContainer),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Follow",
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
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
                ),
                color = MaterialTheme.colorScheme.surfaceContainerHighest
            ) {
                Row(
                    modifier = Modifier
                        .width(125.dp)
                        .height(45.dp)
                        .padding(4.dp)
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .width(125.dp)
                            .height(40.dp),
                        onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } },
                        shape = RoundedCornerShape(5.dp),
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        shadowElevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.tertiaryContainer),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Items",
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }
                    }
                }
            }
        }
    }
}