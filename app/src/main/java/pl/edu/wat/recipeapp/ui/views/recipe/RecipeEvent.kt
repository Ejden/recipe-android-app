package pl.edu.wat.recipeapp.ui.views.recipe

import pl.edu.wat.recipeapp.domain.Recipe

sealed class RecipeEvent {
    object AddToFavourite : RecipeEvent()
    object RemoveFromFavourite : RecipeEvent()
    object StartCooking : RecipeEvent()
    data class AddToShoppingList(val recipe: Recipe) : RecipeEvent()
    data class ChangeServingsQuantity(val recipe: Recipe, val newQuantity: Int) : RecipeEvent()
}
