package pl.edu.wat.recipeapp.domain

import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {
    suspend fun getShoppingList(id: ShoppingListId): ShoppingList
    fun getAllShoppingLists(): Flow<List<ShoppingList>>
    suspend fun saveShoppingList(shoppingList: ShoppingList)
    suspend fun saveShoppingListItem(
        shoppingListItem: ShoppingListItem,
        shoppingListId: ShoppingListId
    )
    suspend fun removeShoppingList(shoppingList: ShoppingList)
    suspend fun removeShoppingListItem(
        shoppingListItem: ShoppingListItem,
        shoppingListId: ShoppingListId
    )
}
