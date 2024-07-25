package com.example.singlefork.ui.screens.home

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    ) {
        Column {
            Text(text = "What's in Your Fridge?", style = MaterialTheme.typography.bodyMedium)
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
        label = { Text(text = label, style = MaterialTheme.typography.labelMedium) },
        leadingIcon = {
            Image(
                modifier = Modifier.size(15.dp),
                painter = painterResource(id = imageIcon),
                contentDescription = label
            )
        }
    )
}

@Composable
fun HelloChef() {
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
            Text(
                text = "Hello, Chef",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "What do you want to cook today?",
                maxLines = 2,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
        }
        Image(
            modifier = Modifier.size(70.dp),
            painter = painterResource(id = R.drawable.cooking),
            contentDescription = null
        )
    }
}