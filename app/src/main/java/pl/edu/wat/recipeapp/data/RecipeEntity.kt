package pl.edu.wat.recipeapp.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class RecipeEntity(
    @PrimaryKey @NonNull val id: UUID = UUID.randomUUID(),
    val name: String,
    val difficulty: String,
    val cookingTime: Int,
    val portions: Int,
//    val ingredients: List<IngredientEntity>,
)

@Entity
data class IngredientEntity(
    @PrimaryKey @NonNull val id: UUID? = null,
    val name: String,
    val quantity: Double,
    val unit: String
)
