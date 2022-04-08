package pl.edu.wat.recipeapp.ui.views.createrecipe

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect
import pl.edu.wat.recipeapp.domain.RecipeDifficulty
import pl.edu.wat.recipeapp.domain.RecipePricing
import pl.edu.wat.recipeapp.ui.theme.DarkGray
import pl.edu.wat.recipeapp.ui.theme.spacing
import pl.edu.wat.recipeapp.ui.viewcomponents.DropdownMenuField
import pl.edu.wat.recipeapp.ui.viewcomponents.DropdownValue
import pl.edu.wat.recipeapp.ui.viewcomponents.NAVIGATION_BAR_HEIGHT
import pl.edu.wat.recipeapp.util.UIEvent

@Composable
fun CreateRecipeView(
    viewModel: CreateRecipeViewModel = hiltViewModel(),
    onNavigate: (UIEvent.Navigate) -> Unit,
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is UIEvent.Navigate -> onNavigate(it)
                else -> Unit
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .scrollable(state = scrollState, orientation = Orientation.Vertical)
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium)
            .padding(bottom = NAVIGATION_BAR_HEIGHT),
    ) {
        Text(
            text = "Create new recipe",
            fontSize = 24.sp,
        )
        Column(
            modifier = Modifier.background(DarkGray),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.name,
                onValueChange = { viewModel.onEvent(CreateRecipeEvent.OnNameChange(it)) },
                singleLine = true,
                label = {
                    Text(text = "Recipe name")
                },
                placeholder = {
                    Text(text = "e.g. Spaghetti Bolognese")
                },
            )
            DropdownMenuField(
                values = RecipeDifficulty.values()
                    .map { DropdownValue(key = it.name, label = stringResource(id = it.idRes)) },
                currentValue = stringResource(id = viewModel.difficulty.idRes),
                label = "Difficulty",
                onValueChange = {
                    viewModel.onEvent(
                        CreateRecipeEvent.OnDifficultyChange(
                            RecipeDifficulty.valueOf(it)
                        )
                    )
                }
            )
            TextField(
                value = viewModel.cookingTime,
                onValueChange = { value ->
                    viewModel.onEvent(
                        CreateRecipeEvent.OnCookingTimeChange(value.filter { it.isDigit() })
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                ),
                label = {
                    Text(text = "Cooking Time")
                }
            )
            DropdownMenuField(
                values = RecipePricing.values()
                    .map { DropdownValue(key = it.name, label = stringResource(id = it.idRes)) },
                currentValue = stringResource(id = viewModel.pricing.idRes),
                label = "Pricing",
                onValueChange = {
                    viewModel.onEvent(
                        CreateRecipeEvent.OnPricingChange(
                            RecipePricing.valueOf(it)
                        )
                    )
                }
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onEvent(CreateRecipeEvent.OnSave) }
        ) {
            Text(text = "Create")
        }
    }
}