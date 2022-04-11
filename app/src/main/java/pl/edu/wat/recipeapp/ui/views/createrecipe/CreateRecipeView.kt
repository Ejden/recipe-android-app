package pl.edu.wat.recipeapp.ui.views.createrecipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.domain.RecipeDifficulty
import pl.edu.wat.recipeapp.domain.RecipePricing
import pl.edu.wat.recipeapp.ui.theme.DarkGray
import pl.edu.wat.recipeapp.ui.theme.VeryLightGray
import pl.edu.wat.recipeapp.ui.theme.spacing
import pl.edu.wat.recipeapp.ui.viewcomponents.DropdownMenuField
import pl.edu.wat.recipeapp.ui.viewcomponents.DropdownValue
import pl.edu.wat.recipeapp.ui.viewcomponents.NAVIGATION_BAR_HEIGHT
import pl.edu.wat.recipeapp.ui.views.createrecipe.views.EditIngredientsView
import pl.edu.wat.recipeapp.util.UIEvent

@Composable
fun CreateRecipeView(
    viewModel: CreateRecipeViewModel = hiltViewModel(),
    onNavigate: (UIEvent.Navigate) -> Unit,
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is UIEvent.Navigate -> onNavigate(it)
                else -> Unit
            }
        }
    }

    LazyColumn(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium)
            .padding(bottom = NAVIGATION_BAR_HEIGHT),
    ) {
        item {
            Text(
                text = stringResource(id = R.string.create_new_recipe),
                style = MaterialTheme.typography.h1
            )
            Divider(color = VeryLightGray)
        }
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                    ),
                    value = viewModel.name,
                    onValueChange = { viewModel.onEvent(CreateRecipeEvent.OnRecipeNameChange(it)) },
                    singleLine = true,
                    label = {
                        Text(
                            text = stringResource(id = R.string.recipe_name),
                            style = MaterialTheme.typography.body1,
                        )
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.recipe_name_placeholder),
                            style = MaterialTheme.typography.body1,
                        )
                    },
                    textStyle = MaterialTheme.typography.body1,
                )
                DropdownMenuField(
                    values = RecipeDifficulty.values()
                        .map {
                            DropdownValue(
                                key = it.name,
                                label = stringResource(id = it.idRes)
                            )
                        },
                    currentValue = stringResource(id = viewModel.difficulty.idRes),
                    label = stringResource(id = R.string.difficulty),
                    onValueChange = {
                        viewModel.onEvent(
                            CreateRecipeEvent.OnDifficultyChange(
                                RecipeDifficulty.valueOf(it)
                            )
                        )
                    }
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
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
                        Text(
                            text = stringResource(id = R.string.cooking_time),
                            style = MaterialTheme.typography.body1,
                        )
                    },
                    textStyle = MaterialTheme.typography.body1,
                )
                DropdownMenuField(
                    values = RecipePricing.values()
                        .map {
                            DropdownValue(
                                key = it.name,
                                label = stringResource(id = it.idRes)
                            )
                        },
                    currentValue = stringResource(id = viewModel.pricing.idRes),
                    label = stringResource(id = R.string.pricing),
                    onValueChange = {
                        viewModel.onEvent(
                            CreateRecipeEvent.OnPricingChange(
                                RecipePricing.valueOf(it)
                            )
                        )
                    }
                )
            }
        }
        item {
            Spacer(
                modifier = Modifier.height(MaterialTheme.spacing.medium)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(color = DarkGray)
                    .padding(all = MaterialTheme.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            ) {
                EditIngredientsView()
            }
        }
        item {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.onEvent(CreateRecipeEvent.OnSave) }
            ) {
                Text(text = stringResource(id = R.string.create))
            }
        }
    }
}
