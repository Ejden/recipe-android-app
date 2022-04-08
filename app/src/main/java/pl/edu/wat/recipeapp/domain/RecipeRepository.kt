package pl.edu.wat.recipeapp.domain

import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun insertRecipe(recipe: Recipe)

    suspend fun removeRecipe(recipe: Recipe)

    suspend fun findRecipe(id: RecipeId): Recipe

    fun getAllRecipes(): Flow<List<Recipe>>
}
