package pl.edu.wat.recipeapp.ui.views.recipe

sealed class RecipeEvent {
    object AddToFavourite : RecipeEvent()
    object RemoveFromFavourite : RecipeEvent()
    object StartCooking : RecipeEvent()
    object AddToShoppingList : RecipeEvent()
    object IncreaseServingsQuantity : RecipeEvent()
    object DecreaseServingsQuantity : RecipeEvent()
    object GoBack : RecipeEvent()
}
