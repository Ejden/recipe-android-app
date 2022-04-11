package pl.edu.wat.recipeapp.data.shopping

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import pl.edu.wat.recipeapp.data.recipe.RecipeEntity
import java.util.UUID

@Entity(
    tableName = "shopping_lists",
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["id"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    val recipeId: UUID,
    val servings: Int
)
