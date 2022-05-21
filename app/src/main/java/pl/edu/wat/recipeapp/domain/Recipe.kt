package pl.edu.wat.recipeapp.domain

import android.net.Uri
import androidx.annotation.StringRes
import pl.edu.wat.recipeapp.R
import java.util.UUID

data class Recipe(
    val id: RecipeId,
    val name: String,
    val difficulty: RecipeDifficulty,
    val cookingTime: Int,
    val portions: Int = 1,
    val isFavourite: Boolean = false,
    val pricing: RecipePricing = RecipePricing.LOW_PRICED,
    val ingredients: List<Ingredient> = emptyList(),
    val cookingSteps: List<CookingStep> = emptyList(),
    val imageUri: Uri? = null,
)

enum class RecipeDifficulty(
    val value: Int,
    @StringRes val idRes: Int
) {
    EASY(1, R.string.easy),
    MEDIUM(2, R.string.medium),
    HARD(3, R.string.hard);
}

enum class RecipePricing(
    @StringRes val idRes: Int
) {
    LOW_PRICED(R.string.low_priced),
    MEDIUM_PRICED(R.string.medium_priced),
    HIGH_PRICED(R.string.high_priced);
}

@JvmInline
value class RecipeId(val raw: UUID) {
    fun printable() = raw.toString()

    companion object {
        fun fromString(raw: String) = RecipeId(UUID.fromString(raw))
        fun generate() = RecipeId(UUID.randomUUID())
    }
}
