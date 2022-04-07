package pl.edu.wat.recipeapp.ui.views.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.edu.wat.recipeapp.domain.RecipeRepository
import pl.edu.wat.recipeapp.navigation.NavigationRoute
import pl.edu.wat.recipeapp.util.UIEvent
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {
    val recipes = repository.getAllRecipes()
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: HomeEvent) = when (event) {
        is HomeEvent.ShowRecipe -> onShowRecipeEvent(event)
    }

    private fun onShowRecipeEvent(event: HomeEvent.ShowRecipe) {
        sendUIEvent(UIEvent.Navigate(
            route = NavigationRoute.Recipe,
            args = listOf(event.recipe.id.printable())
        ))
    }

    private fun sendUIEvent(event: UIEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
