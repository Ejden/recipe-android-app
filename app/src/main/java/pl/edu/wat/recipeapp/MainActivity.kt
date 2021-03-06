package pl.edu.wat.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.edu.wat.recipeapp.navigation.NavigationRoute
import pl.edu.wat.recipeapp.navigation.NavigationView
import pl.edu.wat.recipeapp.ui.theme.RecipeAppTheme
import pl.edu.wat.recipeapp.ui.viewcomponents.NavigationBarView
import pl.edu.wat.recipeapp.ui.viewcomponents.NavigationItem

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        NavigationBarView(
                            items = listOf(
                                NavigationItem(
                                    name = stringResource(id = R.string.navbar_home),
                                    route = NavigationRoute.Home,
                                    icon = Icons.Default.Home
                                ),
                                NavigationItem(
                                    name = stringResource(id = R.string.navbar_create),
                                    route = NavigationRoute.CreateRecipe,
                                    icon = Icons.Default.Create
                                ),
                                NavigationItem(
                                    name = stringResource(id = R.string.navbar_shopping),
                                    route = NavigationRoute.ShoppingLists,
                                    icon = Icons.Default.ShoppingCart
                                ),
                                NavigationItem(
                                    name = stringResource(id = R.string.navbar_favourites),
                                    route = NavigationRoute.Favourites,
                                    icon = Icons.Default.Favorite
                                )
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route.rawRoute)
                            }
                        )
                    }
                ) {
                    NavigationView(navController = navController)
                }
            }
        }
    }
}
