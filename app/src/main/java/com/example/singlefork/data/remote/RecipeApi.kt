package com.example.singlefork.data.remote

import com.example.singlefork.domain.model.Recipes
import retrofit2.http.GET

interface RecipeApi {
    @GET("recipes")
    suspend fun getAllRecipes(): Recipes
}