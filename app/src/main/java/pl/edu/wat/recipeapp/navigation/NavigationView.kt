package pl.edu.wat.recipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import pl.edu.wat.recipeapp.ui.views.createrecipe.CreateRecipeView
import pl.edu.wat.recipeapp.ui.views.developermenu.DeveloperMenuView
import pl.edu.wat.recipeapp.ui.views.home.HomeView
import pl.edu.wat.recipeapp.ui.views.favorite.ProfileView
import pl.edu.wat.recipeapp.ui.views.recipe.RecipeView
import pl.edu.wat.recipeapp.ui.views.shoppinglist.ShoppingListItemsView
import pl.edu.wat.recipeapp.ui.views.shoppinglists.ShoppingListView

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
            ShoppingListView(
                onNavigate = {
                    navController.navigate(it.route.withArgs(it.args))
                }
            )
        }
        composable(route = NavigationRoute.Favorites.rawRoute) {
            ProfileView(
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
                    navController.navigate(it.route.withoutArgs())
                }
            )
        }
        composable(
            route = NavigationRoute.ShoppingListItems.rawRoute + "/{listId}",
            arguments = listOf(
                navArgument("listId") {
                    type = NavType.StringType
                }
            )
        ) {
            ShoppingListItemsView()
        }
        composable(route = NavigationRoute.DeveloperMenu.rawRoute) {
            DeveloperMenuView()
        }
    }
}
