package pl.edu.wat.recipeapp.ui.views.createrecipe.views

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.sp
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.domain.CookingStep
import pl.edu.wat.recipeapp.ui.theme.Blue
import pl.edu.wat.recipeapp.ui.theme.White
import pl.edu.wat.recipeapp.ui.views.createrecipe.CreateRecipeEvent

@Composable
fun EditCookingStepView(
    cookingSteps: List<CookingStep>,
    newCookingStepName: String,
    newCookingStepDescription: String,
    onEvent: (CreateRecipeEvent) -> Unit,
) {

    Text(
        text = stringResource(id = R.string.cooking_steps),
        fontSize = 20.sp,
    )

    cookingSteps.map {
        EditCookingStepViewItem(cookingStep = it, onEvent = onEvent)
    }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
        ),
        value = newCookingStepName,
        onValueChange = {
            onEvent(
                CreateRecipeEvent.OnCookingStepNameChange(it)
            )
        },
        label = {
            Text(
                text = stringResource(id = R.string.cooking_step_name),
                style = MaterialTheme.typography.body1,
            )
        },
        textStyle = MaterialTheme.typography.body1,
    )

    TextField(
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
        ),
        value = newCookingStepDescription,
        onValueChange = {
            onEvent(
                CreateRecipeEvent.OnCookingStepDescriptionChange(it)
            )
        },
        label = {
            Text(
                text = stringResource(id = R.string.cooking_step_description),
                style = MaterialTheme.typography.body1,
            )
        },
        textStyle = MaterialTheme.typography.body1,
    )

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onEvent(CreateRecipeEvent.OnCookingStepAdd) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = White,
        ),
    ) {
        Text(
            text = stringResource(id = R.string.add_cooking_step),
            color = Blue
        )
    }
}