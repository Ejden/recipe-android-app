package pl.edu.wat.recipeapp.ui.views.favourite

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.navigation.NavigationRoute
import pl.edu.wat.recipeapp.ui.theme.Blue
import pl.edu.wat.recipeapp.ui.theme.VeryLightGray
import pl.edu.wat.recipeapp.ui.theme.spacing
import pl.edu.wat.recipeapp.ui.views.favourite.view.FavouriteViewItem
import pl.edu.wat.recipeapp.util.UIEvent

@Composable
fun FavouriteView(
    viewModel: FavouriteViewModel = hiltViewModel(),
    onNavigate: (UIEvent.Navigate) -> Unit
) {
    val favourites by viewModel.favourites.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UIEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
            .padding(bottom = 40.dp)
    ) {
        Text(
            text = stringResource(id = R.string.favourites),
            style = MaterialTheme.typography.h1
        )
        Divider(color = VeryLightGray)

        LazyColumn(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.medium)
                .fillMaxWidth(),
            content = {
                favourites.windowed(size = 2, step = 2, partialWindows = true).map { row ->
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                        ) {
                            row.map {
                                FavouriteViewItem(
                                    modifier = Modifier.weight(1f),
                                    favourite = it,
                                )
                            }
                        }
                    }
                }
            })

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(128.dp)
                .background(Blue)
                .clickable {
                    onNavigate(UIEvent.Navigate(NavigationRoute.DeveloperMenu))
                }
        ) {
            Text(text = "Developer menu")
        }
    }
}
