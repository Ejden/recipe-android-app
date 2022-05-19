package pl.edu.wat.recipeapp.ui.views.createrecipe

import pl.edu.wat.recipeapp.domain.CookingStepId
import pl.edu.wat.recipeapp.domain.IngredientId
import pl.edu.wat.recipeapp.domain.MeasurementUnit
import pl.edu.wat.recipeapp.domain.RecipeDifficulty
import pl.edu.wat.recipeapp.domain.RecipePricing

sealed class CreateRecipeEvent {
    data class OnRecipeNameChange(val name: String) : CreateRecipeEvent()
    data class OnDifficultyChange(val difficulty: RecipeDifficulty) : CreateRecipeEvent()
    data class OnCookingTimeChange(val cookingTime: String) : CreateRecipeEvent()
    data class OnPricingChange(val pricing: RecipePricing) : CreateRecipeEvent()

    data class OnIngredientNameChange(val name: String) : CreateRecipeEvent()
    data class OnIngredientQuantityChange(val quantity: String) : CreateRecipeEvent()
    data class OnIngredientUnitChange(val unit: MeasurementUnit) : CreateRecipeEvent()
    object OnIngredientAdd : CreateRecipeEvent()
    data class OnIngredientRemove(val ingredientId: IngredientId) : CreateRecipeEvent()

    data class OnCookingStepNameChange(val name: String): CreateRecipeEvent()
    data class OnCookingStepDescriptionChange(val description: String): CreateRecipeEvent()
    object OnCookingStepAdd : CreateRecipeEvent()
    data class OnCookingStepRemove(val cookingStepId: CookingStepId) : CreateRecipeEvent()

    object OnSave : CreateRecipeEvent()
}