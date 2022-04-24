package pl.edu.wat.recipeapp.ui.views.shoppinglist.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.domain.ShoppingList
import pl.edu.wat.recipeapp.ui.theme.Blue
import pl.edu.wat.recipeapp.ui.theme.spacing
import pl.edu.wat.recipeapp.ui.views.shoppinglist.ShoppingListEvent

@Composable
fun ShoppingListHeaderView(
    shoppingList: ShoppingList,
    onEvent: (ShoppingListEvent) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(.85f)
        ) {
            Text(
                fontSize = 18.sp,
                text = shoppingList.recipe.name,
                style = MaterialTheme.typography.body1,
            )
            Text(
                modifier = Modifier
                    .clickable { onEvent(ShoppingListEvent.OnGoToRecipeClick) },
                color = Blue,
                text = stringResource(id = R.string.go_to_recipe)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            shoppingList.servings.also {
                Text(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    text = it.toString()
                )
                Text(
                    fontSize = 12.sp,
                    text = stringResource(
                        id = if (it == 1) R.string.serving else R.string.servings
                    )
                )
            }
        }
    }
}