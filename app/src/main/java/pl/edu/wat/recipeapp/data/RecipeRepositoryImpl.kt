package pl.edu.wat.recipeapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.domain.RecipeId
import pl.edu.wat.recipeapp.domain.RecipeRepository
import java.util.UUID

class RecipeRepositoryImpl(private val repository: RecipeRoomRepository) : RecipeRepository {
    override suspend fun insertRecipe(recipe: Recipe) {
        repository.insertRecipe(recipe.toEntity())
    }

    override suspend fun removeRecipe(recipe: Recipe) {
        repository.removeRecipe(recipe.toEntity())
    }

    override suspend fun findRecipe(id: UUID): Recipe {
        return repository.findRecipe(id).toDomain()
    }

    override fun getAllRecipes(): Flow<List<Recipe>> {
        return repository.getAllRecipes().map { list -> list.map { it.toDomain() } }
    }
}

private fun Recipe.toEntity() = RecipeEntity(
    id = id?.raw!!,
    name = name,
    difficulty = difficulty,
    cookingTime = cookingTime,
    portions = portions
)

private fun RecipeEntity.toDomain() = Recipe(
    id = RecipeId(id),
    name = name,
    difficulty = difficulty,
    cookingTime = cookingTime,
    portions = portions
)
