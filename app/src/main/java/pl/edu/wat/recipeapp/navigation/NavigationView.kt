package pl.edu.wat.recipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import pl.edu.wat.recipeapp.ui.views.cooking.CookingView
import pl.edu.wat.recipeapp.ui.views.createrecipe.CreateRecipeView
import pl.edu.wat.recipeapp.ui.views.developermenu.DeveloperMenuView
import pl.edu.wat.recipeapp.ui.views.favourite.FavouriteView
import pl.edu.wat.recipeapp.ui.views.home.HomeView
import pl.edu.wat.recipeapp.ui.views.recipe.RecipeView
import pl.edu.wat.recipeapp.ui.views.shoppinglist.ShoppingListView
import pl.edu.wat.recipeapp.ui.views.shoppinglists.ShoppingListsView

@Composable
fun NavigationView(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationRoute.Home.rawRoute) {
        composable(route = NavigationRoute.Home.rawRoute) {
            HomeView(
                onNavigate = {
                    navController.navigate(it.route.withArgs(it.args))
                }
            )
        }
        composable(route = NavigationRoute.CreateRecipe.rawRoute) {
            CreateRecipeView(
                onNavigate = {
                    navController.navigate(it.route.withArgs(it.args))
                }
            )
        }
        composable(route = NavigationRoute.ShoppingLists.rawRoute) {
            ShoppingListsView(
                onNavigate = {
                    navController.navigate(it.route.withArgs(it.args))
                }
            )
        }
        composable(route = NavigationRoute.Favourites.rawRoute) {
            FavouriteView(
                onNavigate = {
                    navController.navigate(it.route.withArgs(it.args))
                }
            )
        }
        composable(
            route = NavigationRoute.Recipe.rawRoute + "/{recipeId}",
            arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.StringType
                }
            )
        ) {
            RecipeView(
                onBackPressed = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.navigate(it.route.withArgs(it.args))
                }
            )
        }
        composable(
            route = NavigationRoute.Cooking.rawRoute + "/{recipeId}",
            arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.StringType
                }
            )
        ) {
            CookingView()
        }
        composable(
            route = NavigationRoute.ShoppingListItems.rawRoute + "/{listId}",
            arguments = listOf(
                navArgument("listId") {
                    type = NavType.StringType
                }
            )
        ) {
            ShoppingListView(
                onBackPressed = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.navigate(it.route.withArgs(it.args))
                }
            )
        }
        composable(route = NavigationRoute.DeveloperMenu.rawRoute) {
            DeveloperMenuView()
        }
    }
}
