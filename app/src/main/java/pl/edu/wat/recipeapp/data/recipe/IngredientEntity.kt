package pl.edu.wat.recipeapp.data.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "ingredients")
data class IngredientEntity(
    @PrimaryKey
    val id: UUID,
    val recipeId: UUID,
    val name: String,
    val quantity: Double,
    val unit: String
)
