package pl.edu.wat.recipeapp.ui.views.recipe.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.ui.theme.DarkBlack
import pl.edu.wat.recipeapp.ui.theme.spacing

@Composable
fun RecipeImageBoxView(
    recipe: Recipe
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
    }
}
