package pl.edu.wat.recipeapp.ui.views.recipe.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.ui.theme.*
import pl.edu.wat.recipeapp.ui.views.recipe.RecipeEvent

@Composable
fun RecipeImageBoxView(
    recipe: Recipe,
    onEvent: (RecipeEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .height(550.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.spaghetti_bolognese),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 1.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            DarkBlack
                        ),
                        startY = 800f
                    )
                )
        )
        Box(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.h2
            )
        }
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(VeryLightGrayWithAlpha)
                    .clickable { onEvent(RecipeEvent.GoBack) }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = White
                )
            }
        }
    }
}
