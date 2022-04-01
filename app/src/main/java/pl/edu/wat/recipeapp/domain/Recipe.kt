package pl.edu.wat.recipeapp.domain

import java.util.UUID

data class Recipe(
    val id: RecipeId? = null,
    val name: String,
    val difficulty: RecipeDifficulty,
    val cookingTime: Int,
    val portions: Int
)

enum class RecipeDifficulty {
    EASY,
    MEDIUM,
    HARD
}

@JvmInline
value class RecipeId(val raw: UUID) {
    fun printable() = raw.toString()
}
