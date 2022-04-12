package pl.edu.wat.recipeapp.ui.views.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.domain.RecipeRepository
import pl.edu.wat.recipeapp.navigation.NavigationRoute
import pl.edu.wat.recipeapp.util.UIEvent
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    repository: RecipeRepository
) : ViewModel() {

    val favourites = repository.getAllFavouriteRecipes()

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: FavouriteViewEvent) {
        when (event) {
            is FavouriteViewEvent.RemoveRecipeFromFavourites -> TODO()
            is FavouriteViewEvent.ShowRecipe -> onShowRecipe(event.recipe)
        }
    }

    private fun onShowRecipe(recipe: Recipe) {
        viewModelScope.launch {
            _uiEvent.send(
                UIEvent.Navigate(
                    route = NavigationRoute.Recipe,
                    args = listOf(recipe.id.printable()),
                )
            )
        }
    }

}