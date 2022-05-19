package pl.edu.wat.recipeapp.ui.views.createrecipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.edu.wat.recipeapp.domain.CookingStep
import pl.edu.wat.recipeapp.domain.Ingredient
import pl.edu.wat.recipeapp.domain.IngredientId
import pl.edu.wat.recipeapp.domain.MeasurementUnit
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.domain.RecipeDifficulty
import pl.edu.wat.recipeapp.domain.RecipeId
import pl.edu.wat.recipeapp.domain.RecipePricing
import pl.edu.wat.recipeapp.domain.RecipeRepository
import pl.edu.wat.recipeapp.navigation.NavigationRoute
import pl.edu.wat.recipeapp.util.UIEvent
import javax.inject.Inject

@HiltViewModel
class CreateRecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    // Recipe

    var name by mutableStateOf("")
        private set

    var difficulty by mutableStateOf(RecipeDifficulty.EASY)
        private set

    var cookingTime by mutableStateOf("")
        private set

    var pricing by mutableStateOf(RecipePricing.LOW_PRICED)
        private set

    var ingredients by mutableStateOf(listOf<Ingredient>())
        private set

    var cookingSteps by mutableStateOf(listOf<CookingStep>())
        private set

    // New ingredient

    var ingredientName by mutableStateOf("")
        private set

    var ingredientQuantity by mutableStateOf("")
        private set

    var ingredientUnit by mutableStateOf(MeasurementUnit.UNIT)
        private set

    // New cooking step

    var cookingStepName by mutableStateOf("")
        private set

    var cookingStepDescription by mutableStateOf("")
        private set

    fun onEvent(event: CreateRecipeEvent) = when (event) {
        is CreateRecipeEvent.OnCookingTimeChange -> cookingTime = event.cookingTime
        is CreateRecipeEvent.OnDifficultyChange -> difficulty = event.difficulty
        is CreateRecipeEvent.OnRecipeNameChange -> name = event.name
        is CreateRecipeEvent.OnPricingChange -> pricing = event.pricing
        is CreateRecipeEvent.OnSave -> onSaveEvent()
        is CreateRecipeEvent.OnIngredientNameChange -> ingredientName = event.name
        is CreateRecipeEvent.OnIngredientQuantityChange -> onIngredientQuantityChange(event.quantity)
        is CreateRecipeEvent.OnIngredientUnitChange -> ingredientUnit = event.unit
        is CreateRecipeEvent.OnIngredientAdd -> onIngredientAdd()
        is CreateRecipeEvent.OnIngredientRemove -> onIngredientRemove(event.ingredientId)
    }

    private fun onSaveEvent() {
        if (!isValid()) {
            return
        }
        viewModelScope.launch {
            val id = RecipeId.generate()
            repository.insertRecipe(
                Recipe(
                    id = id,
                    name = name,
                    difficulty = difficulty,
                    cookingTime = cookingTime.parseInt(),
                    pricing = pricing,
                    ingredients = ingredients,
                )
            )
            _uiEvent.send(
                UIEvent.Navigate(
                    route = NavigationRoute.Recipe,
                    args = listOf(id.printable()),
                )
            )
        }
    }

    private fun onIngredientQuantityChange(quantity: String) {
        ingredientQuantity = quantity
            .split('.', ',')
            .take(2)
            .joinToString(".") { it.filter { char -> char.isDigit() } }
    }


    private fun isValid(): Boolean {
        return name.isNotBlank()
                && cookingTime.isNotBlank()
    }

    private fun String.parseInt(): Int {
        if (this == "") {
            return 0
        }
        return this.filter { it.isDigit() }.toInt()
    }

    private fun onIngredientAdd() {
        if (!isIngredientValid()) {
            return
        }
        ingredients = ingredients + Ingredient(
            id = IngredientId.generate(),
            name = ingredientName,
            quantity = ingredientQuantity.toDoubleWithTrailingZero(),
            unit = ingredientUnit,
        )
        ingredientName = ""
        ingredientQuantity = ""
        ingredientUnit = MeasurementUnit.UNIT
    }

    private fun String.toDoubleWithTrailingZero(): Double {
        return if (this.endsWith('.')) {
            "${this}0".toDouble()
        } else {
            this.toDouble()
        }
    }

    private fun isIngredientValid(): Boolean {
        return ingredientName.isNotBlank()
                && ingredientQuantity.isNotBlank()
    }

    private fun onIngredientRemove(id: IngredientId) {
        ingredients = ingredients.filter { it.id != id }
    }
}
