package pl.edu.wat.recipeapp.ui.views.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.ui.theme.VeryLightGray
import pl.edu.wat.recipeapp.ui.views.recipe.views.RecipeActionPanelView
import pl.edu.wat.recipeapp.ui.views.recipe.views.RecipeDifficultyView
import pl.edu.wat.recipeapp.ui.views.recipe.views.RecipeImageBoxView
import pl.edu.wat.recipeapp.ui.views.recipe.views.RecipeIngredientsView
import pl.edu.wat.recipeapp.util.UIEvent

@Composable
fun RecipeView(
    onBackPressed: () -> Unit,
    onNavigate: (UIEvent.Navigate) -> Unit,
    viewModel: RecipeViewModel = hiltViewModel()
) {
    val recipe = viewModel.recipe
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UIEvent.Navigate -> onNavigate(event)
                is UIEvent.GoBack -> onBackPressed()
                is UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    if (recipe == null) {
        Scaffold {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.recipe_loading_msg),
                    style = MaterialTheme.typography.h1
                )
            }
        }
    } else {
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    RecipeImageBoxView(recipe) {
                        when (it) {
                            is RecipeEvent.GoBack -> onBackPressed()
                            else -> Unit
                        }
                    }
                }
                item {
                    Divider(thickness = 1.dp, color = VeryLightGray)
                }
                item {
                    RecipeActionPanelView(viewModel.isFavouriteRecipe) {
                        viewModel.onEvent(it)
                    }
                }
                item {
                    Divider(thickness = 1.dp, color = VeryLightGray)
                }
                item { 
                    RecipeDifficultyView(recipe)
                }
                item {
                    Divider(thickness = 1.dp, color = VeryLightGray)
                }
                item {
                    RecipeIngredientsView(recipe, viewModel.servings) {
                        viewModel.onEvent(it)
                    }
                }
            }
        }
    }
}
