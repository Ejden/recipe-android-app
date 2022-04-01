package pl.edu.wat.recipeapp.ui.views.createrecipe

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.sp
import pl.edu.wat.recipeapp.domain.RecipeDifficulty
import pl.edu.wat.recipeapp.ui.theme.DarkGray
import pl.edu.wat.recipeapp.ui.theme.LightGray
import pl.edu.wat.recipeapp.ui.theme.spacing
import pl.edu.wat.recipeapp.viewcomponents.NAVIGATION_BAR_HEIGHT

@Composable
fun CreateRecipeView() {
    var name by remember { mutableStateOf("") }
    var difficulty by remember { mutableStateOf(RecipeDifficulty.EASY) }
    var cookingTime by remember { mutableStateOf(0) }
    var portions by remember { mutableStateOf(0) }

    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    var difficultySelectExpanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .scrollable(state = scrollState, orientation = Orientation.Vertical)
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium)
            .padding(bottom = NAVIGATION_BAR_HEIGHT),
    ) {
        Text(
            text = "Create new recipe",
            fontSize = 24.sp,
        )
        Column(
            modifier = Modifier.background(DarkGray),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { name = it },
                singleLine = true,
                label = {
                    Text(text = "Recipe name")
                },
                placeholder = {
                    Text(text = "e.g. Spaghetti Bolognese")
                },
            )
            Box {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { state -> difficultySelectExpanded = state.hasFocus },
                    readOnly = true,
                    value = difficulty.displayName,
                    onValueChange = {},
                    singleLine = true,
                    label = {
                        Text(text = "Difficulty")
                    }
                )
                DropdownMenu(
                    modifier = Modifier
                        .background(LightGray),
                    expanded = difficultySelectExpanded,
                    onDismissRequest = {
                        difficultySelectExpanded = false
                        focusManager.clearFocus()
                    }
                ) {
                    RecipeDifficulty.values()
                        .forEach {
                            DropdownMenuItem(onClick = {
                                difficulty = it
                                difficultySelectExpanded = false
                                focusManager.clearFocus()
                            }) {
                                Text(text = it.displayName)
                            }
                        }
                }
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Create")
        }
    }
}
