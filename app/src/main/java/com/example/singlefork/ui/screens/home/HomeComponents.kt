package com.example.singlefork.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.singlefork.R

@Composable
fun WhatsInYourFridge() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Text(
                text = "What's in Your Fridge?",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(state = rememberScrollState())
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                CustomChip("Fried Rice", R.drawable.rice)
                Spacer(
                    modifier = Modifier
                        .width(5.dp)
                        .background(MaterialTheme.colorScheme.surface)
                )
                CustomChip("Dosa", R.drawable.dosa)
                Spacer(
                    modifier = Modifier
                        .width(5.dp)
                        .background(MaterialTheme.colorScheme.surface)
                )
                CustomChip("Biryani", R.drawable.biryani)
                Spacer(
                    modifier = Modifier
                        .width(5.dp)
                        .background(MaterialTheme.colorScheme.surface)
                )
                CustomChip("Milkshake", R.drawable.smoothie)
                Spacer(
                    modifier = Modifier
                        .width(5.dp)
                        .background(MaterialTheme.colorScheme.surface)
                )
                CustomChip("Cocktail", R.drawable.cocktail)
                Spacer(
                    modifier = Modifier
                        .width(5.dp)
                        .background(MaterialTheme.colorScheme.surface)
                )
                CustomChip("Falafel", R.drawable.falafel)
                Spacer(
                    modifier = Modifier
                        .width(5.dp)
                        .background(MaterialTheme.colorScheme.surface)
                )
                CustomChip("Roti", R.drawable.flatbread)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(state = rememberScrollState())
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                CustomChip("Bruschetta", R.drawable.bruschetta)
                Spacer(
                    modifier = Modifier
                        .width(5.dp)
                        .background(MaterialTheme.colorScheme.surface)
                )
                CustomChip("Chicken Karahi", R.drawable.chicken_karahi)
                Spacer(
                    modifier = Modifier
                        .width(5.dp)
                        .background(MaterialTheme.colorScheme.surface)
                )
                CustomChip("Curry", R.drawable.curry)
                Spacer(
                    modifier = Modifier
                        .width(5.dp)
                        .background(MaterialTheme.colorScheme.surface)
                )
                CustomChip("Pizza", R.drawable.pizza)
                Spacer(
                    modifier = Modifier
                        .width(5.dp)
                        .background(MaterialTheme.colorScheme.surface)
                )
                CustomChip("Shrimp", R.drawable.shrimp)
                Spacer(
                    modifier = Modifier
                        .width(5.dp)
                        .background(MaterialTheme.colorScheme.surface)
                )
                CustomChip("Hotpot", R.drawable.hotpot)
                Spacer(
                    modifier = Modifier
                        .width(5.dp)
                        .background(MaterialTheme.colorScheme.surface)
                )
                CustomChip("Pasta", R.drawable.pasta)
            }
        }
    }
}

@Composable
fun CustomChip(label: String, imageIcon: Int) {
    var selected by remember { mutableStateOf(false) }
    var selectedColor by remember { mutableStateOf(Color.Transparent) }
    FilterChip(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        onClick = { selected = !selected },
        label = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(15.dp)
                        .background(selectedColor),
                    painter = painterResource(id = imageIcon),
                    contentDescription = label
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    modifier = Modifier,
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                selectedColor = MaterialTheme.colorScheme.primaryContainer
            }
        } else {
            null
        },
    )
}

@Composable
fun HelloChef() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Text(
                text = "Hello, Chef",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .background(MaterialTheme.colorScheme.surface)
            )
            Text(
                text = "What do you want to cook today?",
                maxLines = 2,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Image(
            modifier = Modifier
                .size(70.dp)
                .background(MaterialTheme.colorScheme.surface),
            painter = painterResource(id = R.drawable.cooking),
            contentDescription = null
        )
    }
}