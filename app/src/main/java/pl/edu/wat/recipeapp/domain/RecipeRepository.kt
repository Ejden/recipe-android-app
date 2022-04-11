package pl.edu.wat.recipeapp.domain

import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun insertRecipe(recipe: Recipe)

    suspend fun findRecipe(id: RecipeId): Recipe

    suspend fun removeRecipe(id: RecipeId)

    fun getAllRecipes(): Flow<List<Recipe>>

    fun getAllFavouriteRecipes(): Flow<List<Recipe>>
}
