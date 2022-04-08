package pl.edu.wat.recipeapp.domain

import java.util.UUID

data class ShoppingList(
    val id: ShoppingListId,
    val recipe: Recipe,
    val shoppingListItems: List<ShoppingListItem>
)

@JvmInline
value class ShoppingListId(val raw: UUID)
