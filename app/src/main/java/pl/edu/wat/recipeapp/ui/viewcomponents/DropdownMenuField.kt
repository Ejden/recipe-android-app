package pl.edu.wat.recipeapp.ui.viewcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import pl.edu.wat.recipeapp.ui.theme.LightGray

data class DropdownValue(
    val key: String,
    val label: String,
)

@Composable
fun DropdownMenuField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    values: List<DropdownValue>,
    currentValue: String,
    label: String,
) {
    val focusManager = LocalFocusManager.current
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { state -> expanded = state.hasFocus },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            ),
            readOnly = true,
            value = currentValue,
            onValueChange = {},
            singleLine = true,
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.body1,
                )
            },
            textStyle = MaterialTheme.typography.body1,
        )
        DropdownMenu(
            modifier = Modifier
                .background(LightGray),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                focusManager.clearFocus()
            }
        ) {
            values.forEach {
                DropdownMenuItem(onClick = {
                    onValueChange(it.key)
                    expanded = false
                    focusManager.clearFocus()
                }) {
                    Text(
                        text = it.label,
                        style = MaterialTheme.typography.body1,
                    )
                }
            }
        }
    }
}
