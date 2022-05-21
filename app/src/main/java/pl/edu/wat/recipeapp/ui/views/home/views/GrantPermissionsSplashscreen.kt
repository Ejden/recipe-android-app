package pl.edu.wat.recipeapp.ui.views.home.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.ui.theme.Blue
import pl.edu.wat.recipeapp.ui.theme.White

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GrantPermissionsSplashscreen(
    permissionState: PermissionState,
) {
    Column {
        Text(text = stringResource(R.string.grant_permission_rationale))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { permissionState.launchPermissionRequest() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = White,
            ),
        ) {
            Text(
                text = stringResource(id = R.string.grant_permissions),
                color = Blue
            )
        }
    }
}