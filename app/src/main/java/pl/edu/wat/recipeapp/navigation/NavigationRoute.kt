package pl.edu.wat.recipeapp.navigation

sealed class NavigationRoute(val rawRoute: String) {
    object Home : NavigationRoute("home")
    object CreateRecipe : NavigationRoute("create_recipe")
    object ShoppingLists : NavigationRoute("shopping_lists")
    object ShoppingListItems : NavigationRoute("shopping_list_items")
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
