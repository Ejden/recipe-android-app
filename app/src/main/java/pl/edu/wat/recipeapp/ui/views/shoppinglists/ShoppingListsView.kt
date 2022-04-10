package pl.edu.wat.recipeapp.ui.views.shoppinglists

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.ui.theme.VeryLightGray
import pl.edu.wat.recipeapp.ui.theme.spacing
import pl.edu.wat.recipeapp.ui.views.shoppinglists.views.ShoppingListItem
import pl.edu.wat.recipeapp.util.UIEvent

@Composable
fun ShoppingListView(
    viewModel: ShoppingListsViewModel = hiltViewModel(),
    onNavigate: (UIEvent.Navigate) -> Unit
) {
    val shoppingLists = viewModel.shoppingLists.collectAsState(emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UIEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
            .padding(bottom = 40.dp)
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
        items(shoppingLists.value.size) {
            ShoppingListItem(
                shoppingList = shoppingLists.value[it],
                modifier = Modifier.padding(top = MaterialTheme.spacing.small)
            ) { list ->
                viewModel.onEvent(ShoppingListsEvent.GoToShoppingListItems(list.id))
            }
        }
    }
}
