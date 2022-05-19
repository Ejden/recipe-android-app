package pl.edu.wat.recipeapp.ui.views.cooking

sealed class CookingEvent {
    object PreviousStepClick : CookingEvent()
    object NextStepClick : CookingEvent()
}
