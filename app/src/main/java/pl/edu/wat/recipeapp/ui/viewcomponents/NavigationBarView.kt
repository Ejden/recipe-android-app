package pl.edu.wat.recipeapp.ui.viewcomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import pl.edu.wat.recipeapp.navigation.NavigationRoute
import pl.edu.wat.recipeapp.ui.theme.Blue
import pl.edu.wat.recipeapp.ui.theme.DarkGray
import pl.edu.wat.recipeapp.ui.theme.White

data class NavigationItem(
    val name: String,
    val route: NavigationRoute,
    val icon: ImageVector
)

@Composable
fun NavigationBarView(
    items: List<NavigationItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (NavigationItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = DarkGray,
        elevation = 5.dp
    ) {
        items.forEach { navigationItem -> 
            val selected = navigationItem.route.rawRoute == backStackEntry.value?.destination?.route
            val color = if (selected) Blue else White
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(navigationItem) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = navigationItem.icon,
                            contentDescription = navigationItem.name,
                            tint = color
                        )
                        Text(
                            text = navigationItem.name,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Light,
                            color = color
                        )
                    }
                }
            )
        }
    }
}
