package pl.edu.wat.recipeapp.ui.views.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.edu.wat.recipeapp.navigation.NavigationRoute
import pl.edu.wat.recipeapp.ui.theme.Blue
import pl.edu.wat.recipeapp.util.UIEvent

@Composable
fun ProfileView(
    onNavigate: (UIEvent.Navigate) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(128.dp)
            .background(Blue)
            .clickable {
                onNavigate(UIEvent.Navigate(NavigationRoute.DeveloperMenu))
            }
    ) {
        Text(text = "Developer menu")
    }
}
