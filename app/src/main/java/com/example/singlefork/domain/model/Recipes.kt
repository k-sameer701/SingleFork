package com.example.singlefork.domain.model

import com.example.singlefork.domain.model.Recipe

data class Recipes(
    val limit: Int,
    val recipes: List<Recipe>,
    val skip: Int,
    val total: Int
)