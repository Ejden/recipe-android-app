package pl.edu.wat.recipeapp.ui.views.shoppinglists

import pl.edu.wat.recipeapp.domain.ShoppingListId

sealed class ShoppingListsEvent {
    data class GoToShoppingListsItems(val id: ShoppingListId) : ShoppingListsEvent()
}
