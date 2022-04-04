package pl.edu.wat.recipeapp.ui.views.recipe

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.domain.RecipeDifficulty
import pl.edu.wat.recipeapp.domain.RecipeId
import pl.edu.wat.recipeapp.domain.RecipePricing
import pl.edu.wat.recipeapp.domain.RecipeRepository
import pl.edu.wat.recipeapp.util.UIEvent
import java.util.UUID
import javax.inject.Inject

private val defaultRecipe = Recipe(
    id = RecipeId(UUID.randomUUID()),
    name = "Something",
    difficulty = RecipeDifficulty.EASY,
    cookingTime = 80,
    portions = 2,
    isFavourite = true,
    pricing = RecipePricing.MEDIUM_PRICED
)

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var recipe by mutableStateOf<Recipe?>(null)
        private set
    var servings by mutableStateOf(1)
        private set
    var isFavouriteRecipe by mutableStateOf(false)
        private set
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val recipeId = RecipeId.fromString(savedStateHandle.get<String>("recipeId")!!)
        viewModelScope.launch {
            recipe = defaultRecipe
            isFavouriteRecipe = defaultRecipe.isFavourite
        }
    }

    fun onEvent(event: RecipeEvent) = when (event) {
        is RecipeEvent.AddToFavourite -> onAddToFavouriteEvent(event)
        is RecipeEvent.RemoveFromFavourite -> onRemoveFromFavourite(event)
        is RecipeEvent.AddToShoppingList -> onAddToShoppingListEvent(event)
        is RecipeEvent.ChangeServingsQuantity -> onChangeServingsQuantity(event)
        is RecipeEvent.StartCooking -> onStartCookingEvent(event)
    }

    private fun onAddToFavouriteEvent(event: RecipeEvent.AddToFavourite) {
        recipe = defaultRecipe.copy(isFavourite = true)
        isFavouriteRecipe = recipe!!.isFavourite
    }

    private fun onRemoveFromFavourite(event: RecipeEvent.RemoveFromFavourite) {
        recipe = defaultRecipe.copy(isFavourite = false)
        isFavouriteRecipe = recipe!!.isFavourite
    }

    private fun onAddToShoppingListEvent(event: RecipeEvent.AddToShoppingList) {

    }

    private fun onChangeServingsQuantity(event: RecipeEvent.ChangeServingsQuantity) {

    }

    private fun onStartCookingEvent(event: RecipeEvent.StartCooking) {

    }
}
