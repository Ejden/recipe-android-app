package pl.edu.wat.recipeapp.domain

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

enum class MeasurementUnit {
    PINCH,
    TEASPOON,
    TABLESPOON,
    CUP,
    MILLILITER,
    LITER,
    GRAM,
    KILOGRAM,
    UNIT
}
