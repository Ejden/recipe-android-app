package pl.edu.wat.recipeapp.ui.views.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import pl.edu.wat.recipeapp.ui.views.home.views.EmptyRecipesView
import pl.edu.wat.recipeapp.ui.views.home.views.FirstRecipeItemView
import pl.edu.wat.recipeapp.ui.views.home.views.GrantPermissionsSplashscreen
import pl.edu.wat.recipeapp.ui.views.home.views.RecipeItemView
import pl.edu.wat.recipeapp.util.UIEvent

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeView(
    onNavigate: (UIEvent.Navigate) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val permissionState = rememberPermissionState(android.Manifest.permission.READ_EXTERNAL_STORAGE)

    if (!permissionState.status.isGranted) {
        GrantPermissionsSplashscreen(
            permissionState = permissionState,
        )
        return
    }

    val recipes = viewModel.recipes.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event -> handleEvent(event, onNavigate) }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.padding(bottom = 60.dp)
    ) {
        if (recipes.value.isEmpty()) {
            EmptyRecipesView()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                item {
                    FirstRecipeItemView(recipe = recipes.value.first()) {
                        viewModel.onEvent(HomeEvent.ShowRecipe(it))
                    }
                }
                if (recipes.value.size > 1) {
                    items(recipes.value.size - 1) { index ->
                        RecipeItemView(recipe = recipes.value[index + 1]) {
                            viewModel.onEvent(it)
                        }
                    }
                }
            }
        }
    }
}

private fun handleEvent(
    event: UIEvent,
    onNavigateEventCallback: (UIEvent.Navigate) -> Unit
) = when (event) {
    is UIEvent.Navigate -> onNavigateEventCallback(event)
    else -> Unit
}
