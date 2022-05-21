package pl.edu.wat.recipeapp.ui.views.developermenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.edu.wat.recipeapp.domain.CookingStep
import pl.edu.wat.recipeapp.domain.CookingStepId
import pl.edu.wat.recipeapp.domain.Ingredient
import pl.edu.wat.recipeapp.domain.IngredientId
import pl.edu.wat.recipeapp.domain.MeasurementUnit
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.domain.RecipeDifficulty
import pl.edu.wat.recipeapp.domain.RecipeId
import pl.edu.wat.recipeapp.domain.RecipePricing
import pl.edu.wat.recipeapp.domain.RecipeRepository
import pl.edu.wat.recipeapp.util.UIEvent
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class DeveloperMenuViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: DeveloperMenuEvent) = when (event) {
        is DeveloperMenuEvent.AddSampleRecipe -> onAddSampleRecipeEvent()
    }

    private fun onAddSampleRecipeEvent() {
        viewModelScope.launch {
            val newRecipe = Recipe(
                id = RecipeId.generate(),
                name = "New recipe ${Instant.now()}",
                difficulty = RecipeDifficulty.HARD,
                cookingTime = 50,
                portions = 3,
                isFavourite = false,
                pricing = RecipePricing.LOW_PRICED,
                ingredients = listOf(
                    Ingredient(
                        id = IngredientId.generate(),
                        name = "milk",
                        quantity = 1.0,
                        unit = MeasurementUnit.CUP
                    ),
                    Ingredient(
                        id = IngredientId.generate(),
                        name = "tomato paste",
                        quantity = 100.0,
                        unit = MeasurementUnit.GRAM
                    ),
                    Ingredient(
                        id = IngredientId.generate(),
                        name = "onion",
                        quantity = 2.0,
                        unit = MeasurementUnit.UNIT
                    ),
                    Ingredient(
                        id = IngredientId.generate(),
                        name = "pepper",
                        quantity = 1.5,
                        unit = MeasurementUnit.TEASPOON
                    )
                ),
                cookingSteps = listOf(
                    CookingStep(
                        id = CookingStepId.generate(),
                        title = "Przygotuj patelnię",
                        description = "Na głębokiej patelni rozgrzej około 2 łyżki oliwy z oliwek.",
                    ),
                    CookingStep(
                        id = CookingStepId.generate(),
                        title = "Wrzuć czosnek, cebulę oraz mięso",
                        description = "Na rozgrzaną patelnię wrzuć czosnek i cebulę, a po chwili " +
                                "dodaj mięso, rozdrabniaj je np. widelcem, tak aby nie powstały " +
                                "grube mięsne grudki.",
                    ),
                    CookingStep(
                        id = CookingStepId.generate(),
                        title = "Dodaj zioła",
                        description = "Do mięsa dodaj zioła oraz koncentrat. Całość podgrzewaj " +
                                "przez chwilę, dodaj passatę (przecier pomidorowy), gotuj na " +
                                "małym ogniu około 30 minut.",
                    ),
                    CookingStep(
                        id = CookingStepId.generate(),
                        title = "Ugotuj makaron",
                        description = "Makaron ugotuj al dente, podawaj go z sosem, serem, i bazylią.",
                    ),
                ),
                imageUri = null,
            )
            repository.insertRecipe(newRecipe)
        }
    }
}
