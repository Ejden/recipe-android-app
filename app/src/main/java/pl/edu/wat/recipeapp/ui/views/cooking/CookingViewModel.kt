package pl.edu.wat.recipeapp.ui.views.cooking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.edu.wat.recipeapp.domain.CookingStep
import pl.edu.wat.recipeapp.domain.RecipeId
import pl.edu.wat.recipeapp.domain.RecipeRepository
import javax.inject.Inject

@HiltViewModel
class CookingViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var cookingSteps by mutableStateOf(emptyList<CookingStep>())

    var currentStepIndex by mutableStateOf(0)
        private set

    val totalSteps
        get() = cookingSteps.size

    val currentStep
        get() = if (cookingSteps.isEmpty()) null else cookingSteps[currentStepIndex]

    val showPrevious
        get() = cookingSteps.isNotEmpty() && currentStepIndex != 0

    val showNext
        get() = cookingSteps.isNotEmpty() && currentStepIndex != cookingSteps.size - 1

    init {
        val recipeId = RecipeId.fromString(savedStateHandle.get<String>("recipeId")!!)
        viewModelScope.launch {
            val recipe = recipeRepository.findRecipe(recipeId)
            cookingSteps = recipe.cookingSteps
        }
    }

    fun onEvent(event: CookingEvent) {
        when (event) {
            CookingEvent.PreviousStepClick -> onPreviousStepClick()
            CookingEvent.NextStepClick -> onNextStepClick()
        }
    }

    private fun onPreviousStepClick() {
        if (showPrevious) currentStepIndex--
    }

    private fun onNextStepClick() {
        if (showNext) currentStepIndex++
    }

}