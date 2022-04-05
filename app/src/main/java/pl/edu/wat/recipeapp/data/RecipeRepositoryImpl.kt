package pl.edu.wat.recipeapp.data

import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.edu.wat.recipeapp.data.relations.RecipeWithIngredients
import pl.edu.wat.recipeapp.domain.Ingredient
import pl.edu.wat.recipeapp.domain.IngredientId
import pl.edu.wat.recipeapp.domain.MeasurementUnit
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.domain.RecipeDifficulty
import pl.edu.wat.recipeapp.domain.RecipeId
import pl.edu.wat.recipeapp.domain.RecipePricing
import pl.edu.wat.recipeapp.domain.RecipeRepository

class RecipeRepositoryImpl(private val repository: RecipeDao) : RecipeRepository {
    @Transaction
    override suspend fun insertRecipe(recipe: Recipe) {
        repository.insertRecipe(recipe.toEntity())
        recipe.ingredients
            .map { it.toEntity(recipe.id) }
            .forEach { repository.insertIngredient(it) }
    }

    @Transaction
    override suspend fun removeRecipe(recipe: Recipe) {
        repository.removeRecipe(recipe.toEntity())
    }

    @Transaction
    override suspend fun findRecipe(id: RecipeId): Recipe {
        return repository.findRecipe(id.raw).toDomain()
    }

    @Transaction
    override fun getAllRecipes(): Flow<List<Recipe>> {
        return repository.getAllRecipes().map { list -> list.map { it.toDomain() } }
    }
}

private fun Recipe.toEntity() = RecipeEntity(
    id = id.raw,
    name = name,
    difficulty = difficulty.name,
    cookingTime = cookingTime,
    portions = portions,
    isFavourite = isFavourite,
    pricing = pricing.name
)

private fun Ingredient.toEntity(recipeId: RecipeId) = IngredientEntity(
    id = id.raw,
    recipeId = recipeId.raw,
    name = name,
    quantity = quantity,
    unit = unit.name
)

private fun RecipeWithIngredients.toDomain() = Recipe(
    id = RecipeId(recipe.id),
    name = recipe.name,
    difficulty = RecipeDifficulty.valueOf(recipe.difficulty),
    cookingTime = recipe.cookingTime,
    portions = recipe.portions,
    isFavourite = recipe.isFavourite,
    pricing = RecipePricing.valueOf(recipe.pricing),
    ingredients = ingredients.map { it.toDomain() }
)

private fun IngredientEntity.toDomain() = Ingredient(
    id = IngredientId(id),
    name = name,
    quantity = quantity,
    unit = MeasurementUnit.valueOf(unit)
)
