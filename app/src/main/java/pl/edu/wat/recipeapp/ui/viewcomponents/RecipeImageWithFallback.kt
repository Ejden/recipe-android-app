package pl.edu.wat.recipeapp.ui.viewcomponents

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import pl.edu.wat.recipeapp.R

@Composable
fun RecipeImageWithFallback(
    modifier: Modifier = Modifier,
    uri: Uri?,
    contentDescription: String?,
    contentScale: ContentScale,
    alignment: Alignment = Alignment.Center,
) {
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    bitmap = uri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images.Media.getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            ImageDecoder.decodeBitmap(source)
        }
    }

    if (bitmap == null) {
        Image(
            modifier = modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.spaghetti_bolognese),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            alignment = alignment,
        )
    } else {
        Image(
            modifier = modifier.fillMaxWidth(),
            bitmap = bitmap!!.asImageBitmap(),
            contentDescription = null,
            contentScale = contentScale,
            alignment = alignment,
        )
    }

}