package pl.edu.wat.recipeapp.ui.views.createrecipe.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.domain.CookingStep
import pl.edu.wat.recipeapp.ui.theme.White
import pl.edu.wat.recipeapp.ui.views.createrecipe.CreateRecipeEvent

@Composable
fun EditCookingStepViewItem(
    cookingStep: CookingStep,
    onEvent: (CreateRecipeEvent) -> Unit,
) {
    val (id, title, description) = cookingStep

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(text = title, fontWeight = FontWeight.SemiBold)
            Text(text = description)
        }

        IconButton(
            modifier = Modifier.size(14.dp),
            onClick = {
                onEvent(CreateRecipeEvent.OnCookingStepRemove(id))
            },
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.delete),
                tint = White,
            )
        }
    }
}