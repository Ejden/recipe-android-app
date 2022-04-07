package pl.edu.wat.recipeapp.ui.views.home.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import pl.edu.wat.recipeapp.R

@Composable
fun EmptyRecipesView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.empty_recipe_list),
            style = MaterialTheme.typography.h1
        )
        Text(
            text = stringResource(id = R.string.create_recipe_msg),
            style = MaterialTheme.typography.body1
        )
    }
}
