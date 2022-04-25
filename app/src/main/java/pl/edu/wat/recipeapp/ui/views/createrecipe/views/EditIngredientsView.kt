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
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.domain.Ingredient
import pl.edu.wat.recipeapp.domain.MeasurementUnit
import pl.edu.wat.recipeapp.ui.theme.Blue
import pl.edu.wat.recipeapp.ui.theme.White
import pl.edu.wat.recipeapp.ui.viewcomponents.DropdownMenuField
import pl.edu.wat.recipeapp.ui.viewcomponents.DropdownValue
import pl.edu.wat.recipeapp.ui.views.createrecipe.CreateRecipeEvent

@Composable
fun EditIngredientsView(
    ingredients: List<Ingredient>,
    newIngredientName: String,
    newIngredientQuantity: String,
    newIngredientUnit: MeasurementUnit,
    onEvent: (CreateRecipeEvent) -> Unit,
) {
    Text(
        text = stringResource(id = R.string.ingredients),
        fontSize = 20.sp,
    )

    ingredients.map {
        EditIngredientsViewItem(ingredient = it, onEvent = onEvent)
    }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
        ),
        value = newIngredientName,
        onValueChange = {
            onEvent(
                CreateRecipeEvent.OnIngredientNameChange(it)
            )
        },
        label = {
            Text(
                text = stringResource(id = R.string.ingredient_name),
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
            value = newIngredientQuantity,
            onValueChange = { value ->
                onEvent(
                    CreateRecipeEvent.OnIngredientQuantityChange(value)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
            label = {
                Text(
                    text = stringResource(id = R.string.quantity),
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
            currentValue = stringResource(id = newIngredientUnit.idRes),
            label = stringResource(id = R.string.unit_label),
            onValueChange = {
                onEvent(
                    CreateRecipeEvent.OnIngredientUnitChange(
                        MeasurementUnit.valueOf(it)
                    )
                )
            }
        )
    }

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onEvent(CreateRecipeEvent.OnIngredientAdd) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = White,
        ),
    ) {
        Text(
            text = stringResource(id = R.string.add_ingredient),
            color = Blue
        )
    }
}