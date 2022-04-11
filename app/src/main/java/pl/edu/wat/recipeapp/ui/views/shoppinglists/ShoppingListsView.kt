package pl.edu.wat.recipeapp.ui.views.shoppinglists

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
    val shoppingLists by viewModel.shoppingLists.collectAsState(emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UIEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    if (shoppingLists.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
                .padding(bottom = 40.dp)
        ) {
            Text(
                text = stringResource(id = R.string.shopping_list),
                style = MaterialTheme.typography.h1
            )
            Divider(color = VeryLightGray)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = R.string.no_shopping_lists),
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )
            }
        }
    } else {
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
            items(shoppingLists.size) {
                ShoppingListItem(
                    shoppingList = shoppingLists[it],
                    modifier = Modifier.padding(top = MaterialTheme.spacing.small)
                ) { list ->
                    viewModel.onEvent(ShoppingListsEvent.GoToShoppingListItems(list.id))
                }
            }
        }
    }
}
