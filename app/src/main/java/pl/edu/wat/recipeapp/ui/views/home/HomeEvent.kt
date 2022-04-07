package pl.edu.wat.recipeapp.ui.views.home

import pl.edu.wat.recipeapp.domain.Recipe

sealed class HomeEvent {
    data class ShowRecipe(val recipe: Recipe): HomeEvent()
}
