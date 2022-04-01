package pl.edu.wat.recipeapp.domain

import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface RecipeRepository {
    suspend fun insertRecipe(recipe: Recipe)

    suspend fun removeRecipe(recipe: Recipe)

    suspend fun findRecipe(id: UUID): Recipe

    fun getAllRecipes(): Flow<List<Recipe>>
}
