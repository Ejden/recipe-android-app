package pl.edu.wat.recipeapp.ui.views.cooking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.ui.theme.Blue
import pl.edu.wat.recipeapp.ui.theme.VeryLightGray
import pl.edu.wat.recipeapp.ui.theme.spacing
import pl.edu.wat.recipeapp.ui.viewcomponents.NAVIGATION_BAR_HEIGHT

@Composable
fun CookingView(
    viewModel: CookingViewModel = hiltViewModel()
) {
    if (viewModel.currentStep == null) {
        return
    }

    Column(
        modifier = Modifier
            .padding(all = MaterialTheme.spacing.small)
            .padding(bottom = NAVIGATION_BAR_HEIGHT)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            fontSize = 60.sp,
            color = VeryLightGray,
            style = MaterialTheme.typography.h1,
            fontWeight = FontWeight.Bold,
            text = "${viewModel.currentStepIndex + 1}/${viewModel.totalSteps}",
        )

        Column {
            Text(
                fontSize = 20.sp,
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.SemiBold,
                text = viewModel.currentStep!!.title,
            )
            Text(
                fontSize = 14.sp,
                text = viewModel.currentStep!!.description,
            )
        }

        Column {
            if (viewModel.showPrevious) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Blue
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.onEvent(CookingEvent.PreviousStepClick) },
                ) {
                    Text(
                        text = stringResource(id = R.string.previous_step),
                        style = MaterialTheme.typography.button
                    )
                }
            }
            if (viewModel.showNext) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Blue
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.onEvent(CookingEvent.NextStepClick) },
                ) {
                    Text(
                        text = stringResource(id = R.string.next_step),
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }
    }
}