package com.example.singlefork.domain.repository

import com.example.singlefork.common.Resource
import com.example.singlefork.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getAllRecipes(): Flow<Resource<List<Recipe>>>
}