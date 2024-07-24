package com.example.singlefork.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.singlefork.RecipeApplication
import com.example.singlefork.common.Resource
import com.example.singlefork.domain.model.Recipe
import com.example.singlefork.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val recipeRepository: RecipeRepository
): ViewModel() {
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes = _recipes

    private val _showErrorToast = MutableStateFlow(false)
    val showErrorToast = _showErrorToast

    init {
        getRecipes()
    }

    private fun getRecipes() = viewModelScope.launch {
        recipeRepository.getAllRecipes().collectLatest { result ->
            when(result) {
                is Resource.Error -> {
                    _showErrorToast.value = true
                }
                is Resource.Success -> {
                    result.data?.let { recipe ->
                        _recipes.update { recipe }
                    }
                }
            }
        }
    }

    companion object{
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RecipeApplication)
                val recipeRepository = application.container.recipeRepository
                RecipeViewModel(recipeRepository = recipeRepository)
            }
        }
    }
}