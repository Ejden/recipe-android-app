package pl.edu.wat.recipeapp.ui.views.developermenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
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
                        name = "Milk",
                        quantity = 1.0,
                        unit = MeasurementUnit.CUP
                    )
                )
            )
        }
    }
}
