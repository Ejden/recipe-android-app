package pl.edu.wat.recipeapp.views.home

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect
import pl.edu.wat.recipeapp.views.home.views.EmptyRecipesView
import pl.edu.wat.recipeapp.views.home.views.FirstRecipeView

@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val recipes = viewModel.recipes.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            
        }
    }
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        if (recipes.value.isEmpty()) {
            EmptyRecipesView()
        } else {
            FirstRecipeView(recipe = recipes.value.first())
        }
    }
}
