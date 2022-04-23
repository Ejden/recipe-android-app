package pl.edu.wat.recipeapp.ui.views.shoppinglist

import pl.edu.wat.recipeapp.domain.ShoppingListItem

sealed class ShoppingListEvent {
    object OnGoBackClick : ShoppingListEvent()
    object OnGoToRecipeClick : ShoppingListEvent()
    object OnRemoveClick : ShoppingListEvent()
    data class OnItemCheckedChange(val shoppingListItem: ShoppingListItem) : ShoppingListEvent()
    data class OnItemRemoved(val shoppingListItem: ShoppingListItem) : ShoppingListEvent()
}