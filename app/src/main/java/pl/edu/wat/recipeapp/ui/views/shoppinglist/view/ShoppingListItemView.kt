package pl.edu.wat.recipeapp.ui.views.shoppinglist.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.edu.wat.recipeapp.domain.ShoppingListItem
import pl.edu.wat.recipeapp.ui.theme.Blue
import pl.edu.wat.recipeapp.ui.theme.VeryLightGray
import pl.edu.wat.recipeapp.ui.theme.White
import pl.edu.wat.recipeapp.ui.theme.spacing
import pl.edu.wat.recipeapp.ui.views.shoppinglist.ShoppingListEvent
import pl.edu.wat.recipeapp.util.toPrintable

@Composable
fun ShoppingListItemView(
    servings: Int,
    item: ShoppingListItem,
    onEvent: (ShoppingListEvent) -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.medium,
                top = MaterialTheme.spacing.extraSmall,
                bottom = MaterialTheme.spacing.extraSmall,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            colors = CheckboxDefaults.colors(
                checkedColor = Blue,
                uncheckedColor = VeryLightGray,
                checkmarkColor = White,
            ),
            checked = item.checked,
            onCheckedChange = { onEvent(ShoppingListEvent.OnItemCheckedChange(item)) }
        )
        item.ingredient.apply {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(fontSize = 16.sp, text = name)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = (quantity * servings).toPrintable())
                    Text(text = stringResource(id = unit.idRes))
                    Icon(
                        modifier = Modifier
                            .width(18.dp)
                            .clickable { onEvent(ShoppingListEvent.OnItemRemoved(item)) },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Remove",
                        tint = White,
                    )
                }
            }
        }
    }
}