package pl.edu.wat.recipeapp.ui.views.favourite.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.ui.theme.LightGray
import pl.edu.wat.recipeapp.ui.theme.spacing

@Composable
fun FavouriteViewItem(
    modifier: Modifier = Modifier,
    favourite: Recipe,
) {
    Column(
        modifier = modifier
            .padding(bottom = MaterialTheme.spacing.small)
            .clip(MaterialTheme.shapes.small)
            .background(LightGray)
            .padding(all = MaterialTheme.spacing.small),
    ) {
        Text(text = favourite.name)
    }
}