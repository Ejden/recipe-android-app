package pl.edu.wat.recipeapp.ui.views.shoppinglists

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import pl.edu.wat.recipeapp.domain.RecipeRepository
import pl.edu.wat.recipeapp.domain.ShoppingListRepository
import pl.edu.wat.recipeapp.util.UIEvent
import javax.inject.Inject

@HiltViewModel
class ShoppingListsViewModel @Inject constructor(
    private val shoppingRepository: ShoppingListRepository,
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    val shoppingLists = shoppingRepository.getAllShoppingLists()

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ShoppingListsEvent) = when (event) {
        is ShoppingListsEvent.GoToShoppingListsItems -> onGoToShoppingList(event)
    }

    private fun onGoToShoppingList(event: ShoppingListsEvent.GoToShoppingListsItems) {
        // TODO
    }
}
