package pl.edu.wat.recipeapp.data.shopping

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "shopping_lists")
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    val recipeId: UUID,
    val servings: Int
)
