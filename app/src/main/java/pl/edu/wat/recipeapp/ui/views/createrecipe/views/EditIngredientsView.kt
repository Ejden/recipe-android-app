package pl.edu.wat.recipeapp.ui.views.createrecipe.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.edu.wat.recipeapp.domain.MeasurementUnit
import pl.edu.wat.recipeapp.ui.theme.Blue
import pl.edu.wat.recipeapp.ui.theme.White
import pl.edu.wat.recipeapp.ui.viewcomponents.DropdownMenuField
import pl.edu.wat.recipeapp.ui.viewcomponents.DropdownValue
import pl.edu.wat.recipeapp.ui.views.createrecipe.CreateRecipeEvent
import pl.edu.wat.recipeapp.ui.views.createrecipe.CreateRecipeViewModel

@Composable
fun EditIngredientsView(
    viewModel: CreateRecipeViewModel = hiltViewModel()
) {
    Text(
        text = "Ingredients",
        fontSize = 20.sp,
    )

    viewModel.ingredients.map {
        EditIngredientsViewItem(ingredient = it)
    }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
        ),
        value = viewModel.ingredientName,
        onValueChange = {
            viewModel.onEvent(
                CreateRecipeEvent.OnIngredientNameChange(it)
            )
        },
        label = {
            Text(
                text = "Ingredient Name",
                style = MaterialTheme.typography.body1,
            )
        },
        textStyle = MaterialTheme.typography.body1,
    )

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(.7f),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            ),
            value = viewModel.ingredientQuantity,
            onValueChange = { value ->
                viewModel.onEvent(
                    CreateRecipeEvent.OnIngredientQuantityChange(value.filter { it.isDigit() })
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
            label = {
                Text(
                    text = "Quantity",
                    style = MaterialTheme.typography.body1,
                )
            },
            textStyle = MaterialTheme.typography.body1,
        )
        DropdownMenuField(
            modifier = Modifier.fillMaxWidth(),
            values = MeasurementUnit.values()
                .map {
                    DropdownValue(
                        key = it.name,
                        label = stringResource(id = it.idRes)
                    )
                },
            currentValue = stringResource(id = viewModel.ingredientUnit.idRes),
            label = "Unit",
            onValueChange = {
                viewModel.onEvent(
                    CreateRecipeEvent.OnIngredientUnitChange(
                        MeasurementUnit.valueOf(it)
                    )
                )
            }
        )
    }

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { viewModel.onEvent(CreateRecipeEvent.OnIngredientAdd) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = White,
        ),
    ) {
        Text(
            text = "Add Ingredient",
            color = Blue
        )
    }
}