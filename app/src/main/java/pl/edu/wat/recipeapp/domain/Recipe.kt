package pl.edu.wat.recipeapp.domain

import java.util.UUID

data class Recipe(
    val id: RecipeId? = null,
    val name: String,
    val difficulty: RecipeDifficulty,
    val cookingTime: Int,
    val portions: Int
)

enum class RecipeDifficulty(val value: Int) {
    EASY(1),
    MEDIUM(2),
    HARD(3);
}

@JvmInline
value class RecipeId(val raw: UUID) {
    fun printable() = raw.toString()
}
