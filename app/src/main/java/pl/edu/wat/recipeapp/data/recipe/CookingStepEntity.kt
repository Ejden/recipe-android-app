package pl.edu.wat.recipeapp.data.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "cooking_step")
data class CookingStepEntity(
    @PrimaryKey
    val id: UUID,
    val recipeId: UUID,
    val title: String,
    val description: String,
)
