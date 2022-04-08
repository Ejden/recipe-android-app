package pl.edu.wat.recipeapp.ui.views.shoppinglists

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.ui.theme.VeryLightGray
import pl.edu.wat.recipeapp.ui.theme.spacing

@Composable
fun ShoppingListView(
    viewModel: ShoppingListsViewModel = hiltViewModel()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
    ) {
        item {
            Text(
                text = stringResource(id = R.string.shopping_list),
                style = MaterialTheme.typography.h1
            )
        }
        item {
            Divider(color = VeryLightGray)
        }
    }
}
