package pl.edu.wat.recipeapp.ui.views.createrecipe.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.domain.Ingredient
import pl.edu.wat.recipeapp.ui.theme.White
import pl.edu.wat.recipeapp.ui.theme.spacing
import pl.edu.wat.recipeapp.ui.views.createrecipe.CreateRecipeEvent
import pl.edu.wat.recipeapp.util.toPrintable

@Composable
fun EditIngredientsViewItem(
    onEvent: (CreateRecipeEvent) -> Unit,
    ingredient: Ingredient,
) {
    val (id, name, quantity, unit) = ingredient

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = name)

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = quantity.toPrintable())
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            Text(text = stringResource(id = unit.idRes))

            IconButton(
                modifier = Modifier.size(14.dp),
                onClick = {
                    onEvent(CreateRecipeEvent.OnIngredientRemove(id))
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(id = R.string.delete),
                    tint = White,
                )
            }
        }
    }
}
