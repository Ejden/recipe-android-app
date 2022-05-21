package pl.edu.wat.recipeapp.ui.views.createrecipe.views

import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import pl.edu.wat.recipeapp.R
import pl.edu.wat.recipeapp.ui.theme.Blue
import pl.edu.wat.recipeapp.ui.theme.White
import pl.edu.wat.recipeapp.ui.views.createrecipe.CreateRecipeEvent

@Composable
fun PickImageView(
    onEvent: (CreateRecipeEvent) -> Unit,
) {
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        onEvent(CreateRecipeEvent.OnImageSelect(uri))
        uri?.let {
            bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            }
        }
    }

    bitmap?.let {
        Image(
            modifier = Modifier.fillMaxWidth(),
            bitmap = it.asImageBitmap(),
            contentDescription = null,
        )
    }

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { launcher.launch("image/*") },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = White,
        ),
    ) {
        Text(
            text = stringResource(id = R.string.pick_image),
            color = Blue
        )
    }

}