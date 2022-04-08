package pl.edu.wat.recipeapp.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import pl.edu.wat.recipeapp.data.recipe.IngredientEntity
import pl.edu.wat.recipeapp.data.recipe.RecipeEntity

data class RecipeWithIngredients(
    @Embedded
    val recipe: RecipeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val ingredients: List<IngredientEntity>
)
