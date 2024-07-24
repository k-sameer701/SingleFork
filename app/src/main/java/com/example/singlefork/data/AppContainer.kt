package com.example.singlefork.data

import com.example.singlefork.common.Constants
import com.example.singlefork.data.remote.RecipeApi
import com.example.singlefork.data.repository.RecipeRepositoryImplementation
import com.example.singlefork.domain.repository.RecipeRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val recipeRepository: RecipeRepository
}

class DefaultAppContainer: AppContainer {
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    private val retrofitService: RecipeApi by lazy {
        retrofit.create(RecipeApi::class.java)
    }

    override val recipeRepository: RecipeRepository by lazy {
        RecipeRepositoryImplementation(retrofitService)
    }
}