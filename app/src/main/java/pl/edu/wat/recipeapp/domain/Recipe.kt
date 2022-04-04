package pl.edu.wat.recipeapp.domain

import java.util.UUID

data class Recipe(
    val id: RecipeId,
    val name: String,
    val difficulty: RecipeDifficulty,
    val cookingTime: Int,
    val portions: Int,
    val isFavourite: Boolean = false,
    val pricing: RecipePricing = RecipePricing.LOW_PRICED,
    val ingredients: List<Ingredient> = emptyList()
)

enum class RecipeDifficulty(val value: Int) {
    EASY(1),
    MEDIUM(2),
    HARD(3);
}

enum class RecipePricing {
    LOW_PRICED,
    MEDIUM_PRICED,
    HIGH_PRICED
}

@JvmInline
value class RecipeId(val raw: UUID) {
    fun printable() = raw.toString()

    companion object {
        fun fromString(raw: String) = RecipeId(UUID.fromString(raw))
        fun generate() = RecipeId(UUID.randomUUID())
    }
}
