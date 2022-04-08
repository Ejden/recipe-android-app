package pl.edu.wat.recipeapp.navigation

sealed class NavigationRoute(val rawRoute: String) {
    object Home : NavigationRoute("home")
    object CreateRecipe : NavigationRoute("create_recipe")
    object ShoppingList : NavigationRoute("shopping")
    object Favorites : NavigationRoute("favorites")
    object Recipe : NavigationRoute("recipe")
    object DeveloperMenu : NavigationRoute("developer_menu")

    fun withArgs(args: List<Any>) = buildString {
        append(rawRoute)
        args.forEach { argument ->
            append("/$argument")
        }
    }

    fun withoutArgs() = rawRoute
}
