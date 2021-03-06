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
                        title = "Przygotuj patelni??",
                        description = "Na g????bokiej patelni rozgrzej oko??o 2 ??y??ki oliwy z oliwek.",
                    ),
                    CookingStep(
                        id = CookingStepId.generate(),
                        title = "Wrzu?? czosnek, cebul?? oraz mi??so",
                        description = "Na rozgrzan?? patelni?? wrzu?? czosnek i cebul??, a po chwili " +
                                "dodaj mi??so, rozdrabniaj je np. widelcem, tak aby nie powsta??y " +
                                "grube mi??sne grudki.",
                    ),
                    CookingStep(
                        id = CookingStepId.generate(),
                        title = "Dodaj zio??a",
                        description = "Do mi??sa dodaj zio??a oraz koncentrat. Ca??o???? podgrzewaj " +
                                "przez chwil??, dodaj passat?? (przecier pomidorowy), gotuj na " +
                                "ma??ym ogniu oko??o 30 minut.",
                    ),
                    CookingStep(
                        id = CookingStepId.generate(),
                        title = "Ugotuj makaron",
                        description = "Makaron ugotuj al dente, podawaj go z sosem, serem, i bazyli??.",
                    ),
                )
            )
            repository.insertRecipe(newRecipe)
        }
    }
}
