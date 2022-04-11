package pl.edu.wat.recipeapp.data.shopping

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import pl.edu.wat.recipeapp.data.recipe.IngredientEntity
import java.util.UUID

@Entity(
    tableName = "shopping_list_items",
    foreignKeys = [
        ForeignKey(
            entity = ShoppingListEntity::class,
            parentColumns = ["id"],
            childColumns = ["shoppingListId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = IngredientEntity::class,
            parentColumns = ["id"],
            childColumns = ["ingredientId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ShoppingListItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    val shoppingListId: UUID,
    val ingredientId: UUID,
    val checked: Boolean
)
