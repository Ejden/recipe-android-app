package pl.edu.wat.recipeapp.views.home.views

import androidx.compose.foundation.Image
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.domain.RecipeDifficulty
import pl.edu.wat.recipeapp.domain.RecipeId
import java.util.UUID

@Composable
fun FirstRecipeView(recipe: Recipe) {
    Image(painter = painterResource(id = R.drawable.spaghetti_bolognese), contentDescription = recipe.name)
    Text(text = "Hello")
}

@Preview
@Composable
fun DefaultFirstRecipeView() {
    FirstRecipeView(recipe = Recipe(
        id = RecipeId(UUID.randomUUID()),
        name = "Spaghetti bolognese",
        difficulty = RecipeDifficulty.MEDIUM,
        cookingTime = 40,
        portions = 4
    ))
}
