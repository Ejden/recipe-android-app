package pl.edu.wat.recipeapp.domain

import java.util.UUID

data class ShoppingList(
    val id: ShoppingListId,
    val recipe: Recipe,
    val servings: Int,
    val shoppingListItems: List<ShoppingListItem>
)

@JvmInline
value class ShoppingListId(val raw: UUID) {
    companion object {
        fun generate() = ShoppingListId(UUID.randomUUID())
    }
}
