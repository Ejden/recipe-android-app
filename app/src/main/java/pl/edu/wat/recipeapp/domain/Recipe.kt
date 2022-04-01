package pl.edu.wat.recipeapp.domain

import java.util.UUID

data class Recipe(
    val id: RecipeId? = null,
    val name: String,
    val difficulty: String,
    val cookingTime: Int,
    val portions: Int
)

@JvmInline
value class RecipeId(val raw: UUID) {
    fun printable() = raw.toString()
}
