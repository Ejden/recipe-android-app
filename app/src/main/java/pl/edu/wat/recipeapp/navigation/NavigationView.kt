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
import pl.edu.wat.recipeapp.ui.views.profile.ProfileView
import pl.edu.wat.recipeapp.ui.views.recipe.RecipeView
import pl.edu.wat.recipeapp.ui.views.shoppinglists.ShoppingListView

@Composable
fun NavigationView(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationRoute.Home.rawRoute) {
        composable(route = NavigationRoute.Home.rawRoute) {
            HomeView(onNavigate = {
                navController.navigate(it.route.withArgs(it.args))
            })
        }
        composable(route = NavigationRoute.CreateRecipe.rawRoute) {
            CreateRecipeView()
        }
        composable(route = NavigationRoute.ShoppingList.rawRoute) {
            ShoppingListView()
        }
        composable(route = NavigationRoute.Profile.rawRoute) {
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
        composable(route = NavigationRoute.DeveloperMenu.rawRoute) {
            DeveloperMenuView()
        }
    }
}
