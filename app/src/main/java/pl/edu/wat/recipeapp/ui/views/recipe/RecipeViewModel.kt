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
import pl.edu.wat.recipeapp.domain.Ingredient
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.domain.RecipeId
import pl.edu.wat.recipeapp.domain.RecipeRepository
import pl.edu.wat.recipeapp.domain.ShoppingListRepository
import pl.edu.wat.recipeapp.util.UIEvent
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val shoppingListRepository: ShoppingListRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var recipe by mutableStateOf<Recipe?>(null)
        private set
    var servings by mutableStateOf(1)
        private set
    var isFavouriteRecipe by mutableStateOf(false)
        private set
    var ingredients by mutableStateOf(emptyList<Ingredient>())
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val recipeId = RecipeId.fromString(savedStateHandle.get<String>("recipeId")!!)
        viewModelScope.launch {
            recipe = recipeRepository.findRecipe(recipeId)
            isFavouriteRecipe = recipe?.isFavourite ?: false
            ingredients = recipe?.ingredients.orEmpty()
        }
    }

    fun onEvent(event: RecipeEvent) = when (event) {
        is RecipeEvent.AddToFavourite -> onAddToFavouriteEvent()
        is RecipeEvent.RemoveFromFavourite -> onRemoveFromFavourite()
        is RecipeEvent.AddToShoppingList -> onAddToShoppingListEvent()
        is RecipeEvent.IncreaseServingsQuantity -> onIncreaseServingsQuantity()
        is RecipeEvent.DecreaseServingsQuantity -> onDecreaseServingsQuantity()
        is RecipeEvent.StartCooking -> onStartCookingEvent()
        is RecipeEvent.GoBack -> onGoBackEvent()
    }

    private fun onAddToFavouriteEvent() {
        recipe?.let {
            if (!it.isFavourite) {
                viewModelScope.launch {
                    recipeRepository.insertRecipe(it.copy(isFavourite = true))
                    recipe = recipeRepository.findRecipe(it.id)
                    isFavouriteRecipe = recipe?.isFavourite ?: false
                }
            }
        }
    }

    private fun onRemoveFromFavourite() {
        recipe?.let {
            if (it.isFavourite) {
                viewModelScope.launch {
                    recipeRepository.insertRecipe(it.copy(isFavourite = false))
                    recipe = recipeRepository.findRecipe(it.id)
                    isFavouriteRecipe = recipe?.isFavourite ?: false
                }
            }
        }
    }

    private fun onAddToShoppingListEvent() {
        viewModelScope.launch {
            shoppingListRepository.saveShoppingList()
        }
    }

    private fun onIncreaseServingsQuantity() {
        servings++
    }

    private fun onDecreaseServingsQuantity() {
        if (servings == 1) {
            return
        }

        servings--
    }

    private fun onStartCookingEvent() {
        // TODO: Cooking screen
    }

    private fun onGoBackEvent() {
        viewModelScope.launch {
            _uiEvent.send(UIEvent.GoBack)
        }
    }
}
