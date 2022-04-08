package pl.edu.wat.recipeapp.ui.views.recipe.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.ui.theme.Blue
import pl.edu.wat.recipeapp.ui.theme.LightGray
import pl.edu.wat.recipeapp.ui.theme.spacing
import pl.edu.wat.recipeapp.ui.views.recipe.RecipeEvent

@Composable
fun RecipeIngredientsView(
    recipe: Recipe,
    servingsQuantity: Int,
    onEvent: (RecipeEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = MaterialTheme.spacing.medium,
                top = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium
            )
    ) {
        Text(
            text = stringResource(id = R.string.ingredients),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = "$servingsQuantity ",
                    style = MaterialTheme.typography.body1
                )
                if (servingsQuantity == 1) {
                    Text(
                        text = stringResource(id = R.string.serving),
                        style = MaterialTheme.typography.body1
                    )
                } else {
                    Text(
                        text = stringResource(id = R.string.servings),
                        style = MaterialTheme.typography.body1
                    )
                }
            }

            Row(
                modifier = Modifier
                    .height(24.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(LightGray)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(24.dp)
                        .width(32.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(LightGray)
                        .clickable { onEvent(RecipeEvent.DecreaseServingsQuantity) }
                ) {
                    Text(
                        text = "-",
                        style = MaterialTheme.typography.h3
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(24.dp)
                        .width(32.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(LightGray)
                        .clickable { onEvent(RecipeEvent.IncreaseServingsQuantity) }
                ) {
                    Text(
                        text = "+",
                        style = MaterialTheme.typography.h3
                    )
                }
            }
        }

        recipe.ingredients.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                ) {
                    Text(
                        text = "${(it.quantity * servingsQuantity).toPrintable()} ",
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = stringResource(id = it.unit.idRes),
                        style = MaterialTheme.typography.body1
                    )
                }
                Row {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
        
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Blue
            ),
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.small)
                .fillMaxWidth(),
            onClick = { onEvent(RecipeEvent.AddToShoppingList) },
        ) {
            Text(
                text = stringResource(id = R.string.add_to_shopping_list),
                style = MaterialTheme.typography.button
            )
        }
    }
}

private fun Double.toPrintable() =
    if (this % 1 == 0.0) "%.0f".format(this)
    else "%.2f".format(this)
