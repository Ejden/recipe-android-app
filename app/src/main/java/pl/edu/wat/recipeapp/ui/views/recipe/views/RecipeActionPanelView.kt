package pl.edu.wat.recipeapp.ui.views.recipe.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.ui.theme.Blue
import pl.edu.wat.recipeapp.ui.theme.White
import pl.edu.wat.recipeapp.ui.theme.Yellow
import pl.edu.wat.recipeapp.ui.theme.spacing
import pl.edu.wat.recipeapp.ui.views.recipe.RecipeEvent

@Composable
fun RecipeActionPanelView(
    isFavouriteRecipe: Boolean,
    onEvent: (RecipeEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .padding(MaterialTheme.spacing.medium)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            val favIconColor = if (isFavouriteRecipe) Yellow else White
            val favText = if (isFavouriteRecipe) R.string.remove_from_fav else R.string.add_to_fav
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = stringResource(id = R.string.add_to_fav),
                tint = favIconColor,
                modifier = Modifier
                    .padding(end = MaterialTheme.spacing.small)
                    .clickable { onFavButtonClick(isFavouriteRecipe, onEvent) }
            )
            Text(
                text = stringResource(id = favText),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .clickable { onFavButtonClick(isFavouriteRecipe, onEvent) }
            )
        }
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = stringResource(id = R.string.start_cooking),
                tint = Blue,
                modifier = Modifier
                    .padding(end = MaterialTheme.spacing.small)
                    .clickable { onEvent(RecipeEvent.StartCooking) }
            )
            Text(
                text = stringResource(id = R.string.start_cooking),
                style = MaterialTheme.typography.body1,
                color = Blue,
                modifier = Modifier
                    .clickable { onEvent(RecipeEvent.StartCooking) })
        }
    }
}

private fun onFavButtonClick(isFavouriteRecipe: Boolean, onEvent: (RecipeEvent) -> Unit) =
    if (isFavouriteRecipe) onEvent(RecipeEvent.RemoveFromFavourite)
    else onEvent(RecipeEvent.AddToFavourite)
