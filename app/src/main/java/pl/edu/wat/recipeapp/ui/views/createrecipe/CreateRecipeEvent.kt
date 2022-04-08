package pl.edu.wat.recipeapp.ui.views.createrecipe

import pl.edu.wat.recipeapp.domain.RecipeDifficulty
import pl.edu.wat.recipeapp.domain.RecipePricing

sealed class CreateRecipeEvent {
    data class OnCookingTimeChange(val cookingTime: String) : CreateRecipeEvent()
    data class OnDifficultyChange(val difficulty: RecipeDifficulty) : CreateRecipeEvent()
    data class OnNameChange(val name: String) : CreateRecipeEvent()
    data class OnPricingChange(val pricing: RecipePricing): CreateRecipeEvent()
    object OnSave : CreateRecipeEvent()
}