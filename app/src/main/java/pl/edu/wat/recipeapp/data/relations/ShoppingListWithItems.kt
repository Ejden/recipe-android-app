package pl.edu.wat.recipeapp.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import pl.edu.wat.recipeapp.data.shopping.ShoppingListEntity
import pl.edu.wat.recipeapp.data.shopping.ShoppingListItemEntity

data class ShoppingListWithItems(
    @Embedded
    val shoppingList: ShoppingListEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "shoppingListId"
    )
    val shoppingListItems: List<ShoppingListItemEntity>
)
