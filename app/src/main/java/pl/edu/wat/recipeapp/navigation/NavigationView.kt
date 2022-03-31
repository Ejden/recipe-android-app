package pl.edu.wat.recipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pl.edu.wat.recipeapp.views.createrecipe.CreateRecipeView
import pl.edu.wat.recipeapp.views.home.HomeView
import pl.edu.wat.recipeapp.views.profile.ProfileView
import pl.edu.wat.recipeapp.views.shopping.ShoppingListView

@Composable
fun NavigationView(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationRoute.Home.rawRoute) {
        composable(route = NavigationRoute.Home.rawRoute) {
            HomeView()
        }
        composable(route = NavigationRoute.CreateRecipe.rawRoute) {
            CreateRecipeView()
        }
        composable(route = NavigationRoute.ShoppingList.rawRoute) {
            ShoppingListView()
        }
        composable(route = NavigationRoute.Profile.rawRoute) {
            ProfileView()
        }
    }
}
