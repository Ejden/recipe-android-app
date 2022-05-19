package pl.edu.wat.recipeapp.domain

import java.util.UUID

data class CookingStep(
    val id: CookingStepId,
    val title: String,
    val description: String,
)

@JvmInline
value class CookingStepId(val raw: UUID) {
    companion object {
        fun generate() = CookingStepId(UUID.randomUUID())
    }
}