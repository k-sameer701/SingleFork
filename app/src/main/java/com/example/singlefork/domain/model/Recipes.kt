package com.example.singlefork.domain.model

data class Recipes(
    val limit: Int,
    val recipes: List<Recipe>,
    val skip: Int,
    val total: Int
)