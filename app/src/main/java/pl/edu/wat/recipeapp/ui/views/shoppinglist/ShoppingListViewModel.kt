package pl.edu.wat.recipeapp.ui.views.shoppinglist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.edu.wat.recipeapp.domain.ShoppingList
import pl.edu.wat.recipeapp.domain.ShoppingListId
import pl.edu.wat.recipeapp.domain.ShoppingListItem
import pl.edu.wat.recipeapp.domain.ShoppingListRepository
import pl.edu.wat.recipeapp.navigation.NavigationRoute
import pl.edu.wat.recipeapp.util.UIEvent
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val shoppingRepository: ShoppingListRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var shoppingList by mutableStateOf<ShoppingList?>(null)

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val shoppingListId =
            ShoppingListId.fromString(savedStateHandle.get<String>("listId")!!)
        viewModelScope.launch {
            shoppingList = shoppingRepository.getShoppingList(shoppingListId)
        }
    }

    fun onEvent(event: ShoppingListEvent) {
        when (event) {
            is ShoppingListEvent.OnGoBackClick -> onGoBackClick()
            is ShoppingListEvent.OnGoToRecipeClick -> onGoToRecipeClick()
            is ShoppingListEvent.OnRemoveClick -> onRemoveClick()
            is ShoppingListEvent.OnItemCheckedChange -> onItemCheckedChange(event.shoppingListItem)
            is ShoppingListEvent.OnItemRemoved -> onItemRemoved(event.shoppingListItem)
        }
    }

    private fun onGoBackClick() {
        viewModelScope.launch {
            _uiEvent.send(UIEvent.GoBack)
        }
    }

    private fun onGoToRecipeClick() {
        shoppingList?.let {
            viewModelScope.launch {
                _uiEvent.send(
                    UIEvent.Navigate(
                        route = NavigationRoute.Recipe,
                        args = listOf(it.recipe.id.printable()),
                    )
                )
            }
        }
    }

    private fun onRemoveClick() {
        shoppingList?.let {
            viewModelScope.launch {
                shoppingRepository.removeShoppingList(it.id)
                _uiEvent.send(UIEvent.GoBack)
            }
            shoppingList = null
        }
    }

    private fun onItemCheckedChange(shoppingListItem: ShoppingListItem) {
        viewModelScope.launch {
            shoppingRepository.saveShoppingListItem(
                shoppingListItem = shoppingListItem.copy(checked = !shoppingListItem.checked),
                shoppingListId = shoppingList!!.id,
            )
            shoppingList = shoppingRepository.getShoppingList(shoppingList!!.id)
        }
    }

    private fun onItemRemoved(shoppingListItem: ShoppingListItem) {
        viewModelScope.launch {
            shoppingRepository.removeShoppingListItem(shoppingListItem.id)
            shoppingList = shoppingRepository.getShoppingList(shoppingList!!.id)
        }
    }
}