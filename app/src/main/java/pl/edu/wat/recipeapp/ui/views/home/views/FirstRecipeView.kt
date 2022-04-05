package pl.edu.wat.recipeapp.ui.views.home.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.domain.RecipeDifficulty
import pl.edu.wat.recipeapp.domain.RecipeId
import pl.edu.wat.recipeapp.ui.theme.LightGray
import pl.edu.wat.recipeapp.ui.theme.White
import pl.edu.wat.recipeapp.ui.theme.spacing
import java.util.UUID

@Composable
fun FirstRecipeItemView(
    recipe: Recipe,
    onRecipeClick: (Recipe) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(550.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.spaghetti_bolognese),
            contentScale = ContentScale.Crop,
            contentDescription = recipe.name,
            modifier = Modifier
                .fillMaxHeight(0.80f)
                .clickable { onRecipeClick(recipe) },
            alignment = Alignment.Center
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .height(100.dp)
                .padding(horizontal = MaterialTheme.spacing.medium)
                .offset(0.dp, -MaterialTheme.spacing.large)
                .clip(MaterialTheme.shapes.medium)
                .background(LightGray)
                .padding(MaterialTheme.spacing.medium)
                .clickable {
                    onRecipeClick(recipe)
                }
        ) {
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.h1,
                color = White
            )
        }
    }
}

@Preview
@Composable
fun DefaultFirstRecipeView() {
    FirstRecipeItemView(recipe = Recipe(
        id = RecipeId(UUID.randomUUID()),
        name = "Spaghetti bolognese",
        difficulty = RecipeDifficulty.MEDIUM,
        cookingTime = 40,
        portions = 4
    )) {

    }
}
