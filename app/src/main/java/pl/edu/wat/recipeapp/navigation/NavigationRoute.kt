package pl.edu.wat.recipeapp.navigation

sealed class NavigationRoute(val rawRoute: String) {
    object Home : NavigationRoute("home")
    object CreateRecipe : NavigationRoute("create_recipe")
    object ShoppingList : NavigationRoute("shopping")
    object Profile : NavigationRoute("profile")

    fun withArgs(vararg args: String) = buildString {
        append(rawRoute)
        args.forEach { argument ->
            append("/$argument")
        }
    }
}
