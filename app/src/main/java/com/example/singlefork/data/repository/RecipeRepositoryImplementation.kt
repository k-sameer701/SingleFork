package com.example.singlefork.data.repository

import com.example.singlefork.common.Resource
import com.example.singlefork.data.remote.RecipeApi
import com.example.singlefork.domain.model.Recipe
import com.example.singlefork.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RecipeRepositoryImplementation(
    private val apiService: RecipeApi
) : RecipeRepository {
    override suspend fun getAllRecipes(): Flow<Resource<List<Recipe>>> {
        return flow {
            val recipeFromApi = try {
                apiService.getAllRecipes()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error due to IO Exception"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error due to Http Exception"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error due to Exception"))
                return@flow
            }
            emit(Resource.Success(data = recipeFromApi.recipes))
        }
    }
}