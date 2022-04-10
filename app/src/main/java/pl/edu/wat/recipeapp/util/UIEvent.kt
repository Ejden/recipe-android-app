package pl.edu.wat.recipeapp.util

import pl.edu.wat.recipeapp.navigation.NavigationRoute

sealed class UIEvent {
    data class Navigate(
        val route: NavigationRoute,
        val args: List<String> = emptyList()
    ): UIEvent()

    object GoBack : UIEvent()

    data class ShowSnackBar(val message: String) : UIEvent()
}
