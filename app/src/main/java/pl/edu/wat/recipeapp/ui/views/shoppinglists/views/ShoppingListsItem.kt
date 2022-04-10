package pl.edu.wat.recipeapp.ui.views.shoppinglists.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.domain.ShoppingList
import pl.edu.wat.recipeapp.ui.theme.White
import pl.edu.wat.recipeapp.ui.theme.spacing

@Composable
fun ShoppingListItem(
    shoppingList: ShoppingList,
    modifier: Modifier = Modifier,
    onClick: (ShoppingList) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .clickable { onClick(shoppingList) }
    ) {
        Image(
            painter = painterResource(id = R.drawable.spaghetti_bolognese),
            contentDescription = shoppingList.recipe.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Text(
            text = shoppingList.recipe.name,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .weight(4f)
                .padding(
                    start = MaterialTheme.spacing.small,
                )
        )
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "open",
            tint = White,
            modifier = Modifier
                .weight(1f)
                .size(20.dp)
                .align(Alignment.CenterVertically)
        )
    }
}