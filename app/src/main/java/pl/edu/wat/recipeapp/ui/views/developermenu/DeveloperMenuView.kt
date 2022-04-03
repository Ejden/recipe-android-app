package pl.edu.wat.recipeapp.ui.views.developermenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pl.edu.wat.recipeapp.ui.theme.Blue

@Composable
fun DeveloperMenuView() {
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
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Generate random recipe")
            }
        }
    }
}
