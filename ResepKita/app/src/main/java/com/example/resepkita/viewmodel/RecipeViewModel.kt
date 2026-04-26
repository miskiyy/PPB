package com.example.resepkita.viewmodel

import androidx.lifecycle.ViewModel
import com.example.resepkita.model.Recipe
import com.example.resepkita.model.dummyRecipes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RecipeViewModel : ViewModel() {
    private val _recipes = MutableStateFlow(dummyRecipes)
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    fun toggleFavorite(recipeId: Int) {
        _recipes.update { currentRecipes ->
            currentRecipes.map { recipe ->
                if (recipe.id == recipeId) {
                    recipe.copy(isFavorite = !recipe.isFavorite)
                } else {
                    recipe
                }
            }
        }
    }

    fun getRecipeById(recipeId: Int): Recipe? {
        return _recipes.value.find { it.id == recipeId }
    }
}
