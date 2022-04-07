package pl.edu.wat.recipeapp.domain

import androidx.annotation.StringRes
import pl.edu.wat.recipeapp.R
import java.util.UUID

data class Ingredient(
    val id: IngredientId,
    val name: String,
    val quantity: Double,
    val unit: MeasurementUnit
)

@JvmInline
value class IngredientId(val raw: UUID) {
    companion object {
        fun generate() = IngredientId(UUID.randomUUID())
    }
}

enum class MeasurementUnit(@StringRes val idRes: Int) {
    PINCH(R.string.pinch),
    TEASPOON(R.string.teaspoon),
    TABLESPOON(R.string.tablespoon),
    CUP(R.string.cup),
    MILLILITER(R.string.milliliter),
    LITER(R.string.liter),
    GRAM(R.string.gram),
    KILOGRAM(R.string.kilogram),
    UNIT(R.string.unit)
}
