package pl.edu.wat.recipeapp.data

import pl.edu.wat.recipeapp.data.recipe.IngredientEntity
import pl.edu.wat.recipeapp.data.recipe.RecipeEntity
import pl.edu.wat.recipeapp.data.relations.RecipeWithIngredients
import pl.edu.wat.recipeapp.domain.Ingredient
import pl.edu.wat.recipeapp.domain.IngredientId
import pl.edu.wat.recipeapp.domain.MeasurementUnit
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.domain.RecipeDifficulty
import pl.edu.wat.recipeapp.domain.RecipeId
import pl.edu.wat.recipeapp.domain.RecipePricing

interface OneWayMapper<T, R> {
    suspend fun map(from: T): R
}

object AsyncCallHandlers {
    suspend fun <T, R> handleDbCall(
        domainMapper: OneWayMapper<T, R>,
        call: suspend () -> T
    ): R {
        return domainMapper.map(call())
    }
}

object RecipeEntityMapper : OneWayMapper<RecipeWithIngredients, Recipe> {
    override suspend fun map(from: RecipeWithIngredients): Recipe {
        return  Recipe(
            id = RecipeId(from.recipe.id),
            name = from.recipe.name,
            difficulty = RecipeDifficulty.valueOf(from.recipe.difficulty),
            cookingTime = from.recipe.cookingTime,
            portions = from.recipe.portions,
            isFavourite = from.recipe.isFavourite,
            pricing = RecipePricing.valueOf(from.recipe.pricing),
            ingredients = from.ingredients.map { IngredientEntityMapper.map(it) }
        )
    }
}

object IngredientEntityMapper : OneWayMapper<IngredientEntity, Ingredient> {
    override suspend fun map(from: IngredientEntity): Ingredient {
        return Ingredient(
            id = IngredientId(from.id),
            name = from.name,
            quantity = from.quantity,
            unit = MeasurementUnit.valueOf(from.unit)
        )
    }
}
