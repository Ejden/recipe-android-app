package pl.edu.wat.recipeapp.ui.views.developermenu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.ui.theme.Blue

@Composable
fun DeveloperMenuView(
    viewModel: DeveloperMenuViewModel = hiltViewModel()
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Button(
                colors = buttonColors(
                    backgroundColor = Blue
                ),
                onClick = {
                    viewModel.onEvent(DeveloperMenuEvent.AddSampleRecipe)
                }
            ) {
                Text(text = stringResource(id = R.string.generate_random_recipe))
            }
        }
    }
}
