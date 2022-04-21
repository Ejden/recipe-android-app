package pl.edu.wat.recipeapp.ui.views.favourite

import pl.edu.wat.recipeapp.domain.Recipe

sealed class FavouriteViewEvent {
    data class ShowRecipe(val recipe: Recipe) : FavouriteViewEvent()
    data class RemoveRecipeFromFavourites(val recipe: Recipe) : FavouriteViewEvent()
}
