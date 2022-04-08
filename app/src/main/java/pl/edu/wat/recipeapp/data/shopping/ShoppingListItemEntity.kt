package pl.edu.wat.recipeapp.data.shopping

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "shopping_list_items")
data class ShoppingListItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    val shoppingListId: UUID,
    val ingredientId: UUID,
    val checked: Boolean
)
