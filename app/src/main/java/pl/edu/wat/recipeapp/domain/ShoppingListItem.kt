package pl.edu.wat.recipeapp.domain

import java.util.UUID

data class ShoppingListItem(
    val id: ShoppingListItemId,
    val ingredient: Ingredient,
    val checked: Boolean
)

@JvmInline
value class ShoppingListItemId(val raw: UUID)
