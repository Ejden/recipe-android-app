package pl.edu.wat.recipeapp.ui.views.recipe.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.domain.Recipe
import pl.edu.wat.recipeapp.domain.RecipeDifficulty
import pl.edu.wat.recipeapp.domain.RecipeId
import pl.edu.wat.recipeapp.domain.RecipePricing
import pl.edu.wat.recipeapp.ui.theme.Blue
import pl.edu.wat.recipeapp.ui.theme.White
import pl.edu.wat.recipeapp.ui.theme.Yellow
import pl.edu.wat.recipeapp.ui.theme.spacing
import java.util.UUID

@Composable
fun RecipeDifficultyView(recipe: Recipe) {
    val fulfill = recipe.cookingTime / 60f
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
            ) {
                CircularProgressIndicator(
                    progress = fulfill,
                    color = Blue,
                    strokeWidth = 1.dp,
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp)
                )
                Text(
                    text = "${recipe.cookingTime} min.",
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(
                text = stringResource(id = R.string.cooking),
                style = MaterialTheme.typography.body1
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
            ) {
                val numberOfDollars = when(recipe.pricing) {
                    RecipePricing.LOW_PRICED -> 1
                    RecipePricing.MEDIUM_PRICED -> 2
                    RecipePricing.HIGH_PRICED -> 3
                }
                PricingWidgetView(numberOfDollars)
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(
                text = stringResource(id = R.string.pricing),
                style = MaterialTheme.typography.body1
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val iconColor = when (recipe.difficulty) {
                RecipeDifficulty.EASY -> Color.Green
                RecipeDifficulty.MEDIUM -> Yellow
                RecipeDifficulty.HARD -> Color.Red
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.chief),
                    contentDescription = "difficulty",
                    tint = iconColor,
                    modifier = Modifier
                        .size(64.dp)
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(
                text = stringResource(id = R.string.difficulty),
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
fun PricingWidgetView(numberOfDollars: Int) {
    when (numberOfDollars) {
        1 -> {
            Text(
                text = "$",
                style = MaterialTheme.typography.h1,
                fontSize = 48.sp,
                fontWeight = FontWeight.Thin
            )
        }
        2 -> {
            Text(
                text = "$",
                style = MaterialTheme.typography.h1,
                fontSize = 48.sp,
                fontWeight = FontWeight.Thin,
                modifier = Modifier
                    .offset((-12).dp)
            )
            Text(
                text = "$",
                style = MaterialTheme.typography.h1,
                fontSize = 48.sp,
                fontWeight = FontWeight.Thin,
                modifier = Modifier
                    .offset(12.dp)
            )
        }
        3 -> {
            Text(
                text = "$",
                style = MaterialTheme.typography.h1,
                fontSize = 48.sp,
                fontWeight = FontWeight.Thin,
                modifier = Modifier
                    .offset((-26).dp),
            )
            Text(
                text = "$",
                style = MaterialTheme.typography.h1,
                fontSize = 48.sp,
                fontWeight = FontWeight.Thin
            )
            Text(
                text = "$",
                style = MaterialTheme.typography.h1,
                fontSize = 48.sp,
                fontWeight = FontWeight.Thin,
                modifier = Modifier
                    .offset(26.dp)
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        RecipeDifficultyView(
            recipe = Recipe(
                id = RecipeId(UUID.randomUUID()),
                name = "Something",
                difficulty = RecipeDifficulty.EASY,
                cookingTime = 80,
                portions = 2,
                favourite = true,
                pricing = RecipePricing.MEDIUM_PRICED
            )
        )
    }
}
