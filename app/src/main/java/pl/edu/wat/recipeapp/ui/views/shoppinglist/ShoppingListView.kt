package pl.edu.wat.recipeapp.ui.views.shoppinglist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.ui.theme.LightGray
import pl.edu.wat.recipeapp.ui.theme.VeryLightGrayWithAlpha
import pl.edu.wat.recipeapp.ui.theme.White
import pl.edu.wat.recipeapp.ui.theme.spacing
import pl.edu.wat.recipeapp.ui.views.shoppinglist.view.ShoppingListHeaderView
import pl.edu.wat.recipeapp.ui.views.shoppinglist.view.ShoppingListItemView
import pl.edu.wat.recipeapp.util.UIEvent

@Composable
fun ShoppingListView(
    viewModel: ShoppingListViewModel = hiltViewModel(),
    onNavigate: (UIEvent.Navigate) -> Unit,
    onBackPressed: () -> Unit,
) {
    val shoppingList = viewModel.shoppingList

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UIEvent.Navigate -> onNavigate(event)
                is UIEvent.GoBack -> onBackPressed()
                else -> Unit
            }
        }
    }

    if (shoppingList == null) {
        Scaffold {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.recipe_loading_msg),
                    style = MaterialTheme.typography.h1
                )
            }
        }
    } else {
        LazyColumn {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.spaghetti_bolognese),
                        contentDescription = viewModel.shoppingList?.recipe?.name ?: "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.medium)
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(VeryLightGrayWithAlpha)
                            .clickable { viewModel.onEvent(ShoppingListEvent.OnGoBackClick) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back),
                            tint = White
                        )
                    }
                }
            }
            item {
                ShoppingListHeaderView(
                    shoppingList = shoppingList,
                    onEvent = { viewModel.onEvent(it) },
                )
            }
            item {
                if (shoppingList.shoppingListItems.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.no_shopping_list_items),
                            style = MaterialTheme.typography.body1,
                        )
                    }
                } else {
                    shoppingList.shoppingListItems
                        .sortedBy { it.ingredient.name }
                        .partition { it.checked }
                        .let { (checked, unchecked) -> unchecked + checked }
                        .map { listItem ->
                            ShoppingListItemView(
                                servings = shoppingList.servings,
                                item = listItem,
                                onEvent = { viewModel.onEvent(it) }
                            )
                        }
                }
            }
            item {
                Button(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.medium)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = LightGray,
                    ),
                    onClick = { viewModel.onEvent(ShoppingListEvent.OnRemoveClick) },
                ) {
                    Text(text = stringResource(id = R.string.remove_shopping_list))
                }
            }
        }
    }
}
