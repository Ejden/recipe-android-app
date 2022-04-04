package pl.edu.wat.recipeapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    val name: String,
    val difficulty: String,
    val cookingTime: Int,
    val portions: Int,
    val isFavourite: Boolean,
    val pricing: String
)
