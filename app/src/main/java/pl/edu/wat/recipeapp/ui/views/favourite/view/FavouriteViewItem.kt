package pl.edu.wat.recipeapp.ui.views.favourite.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.ui.theme.DarkBlack
import pl.edu.wat.recipeapp.ui.theme.LightGray
import pl.edu.wat.recipeapp.ui.theme.White
import pl.edu.wat.recipeapp.ui.theme.spacing
import pl.edu.wat.recipeapp.ui.views.favourite.FavouriteViewEvent

@Composable
fun FavouriteViewItem(
    modifier: Modifier = Modifier,
    favourite: Recipe,
    onEvent: (FavouriteViewEvent) -> Unit,
) {
    Box(
        modifier = modifier
            .padding(bottom = MaterialTheme.spacing.small)
            .clip(MaterialTheme.shapes.medium)
            .background(LightGray)
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onEvent(FavouriteViewEvent.ShowRecipe(favourite)) },
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(.8f),
                    text = favourite.name,
                )
                Icon(
                    modifier = Modifier
                        .width(16.dp)
                        .clickable {
                            onEvent(
                                FavouriteViewEvent.RemoveRecipeFromFavourites(favourite)
                            )
                        },
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.remove),
                    tint = White,
                )
            }
        }
    }
}