package pl.edu.wat.recipeapp.ui.views.favourite.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.ui.theme.DarkBlack
import pl.edu.wat.recipeapp.ui.theme.LightGray
import pl.edu.wat.recipeapp.ui.theme.spacing
import pl.edu.wat.recipeapp.ui.views.favourite.FavouriteViewEvent
import pl.edu.wat.recipeapp.ui.views.favourite.FavouriteViewModel

@Composable
fun FavouriteViewItem(
    modifier: Modifier = Modifier,
    favourite: Recipe,
    viewModel: FavouriteViewModel = hiltViewModel()
) {
    Box(
        modifier = modifier
            .padding(bottom = MaterialTheme.spacing.small)
            .clip(MaterialTheme.shapes.medium)
            .background(LightGray)
            .fillMaxWidth()
            .height(200.dp)
            .clickable { viewModel.onEvent(FavouriteViewEvent.ShowRecipe(favourite)) },
    ) {
        Image(
            painter = painterResource(id = R.drawable.spaghetti_bolognese),
            contentDescription = favourite.name,
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            DarkBlack,
                        ),
                        startY = 100f,
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(MaterialTheme.spacing.small),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Text(text = favourite.name)
        }
    }
}