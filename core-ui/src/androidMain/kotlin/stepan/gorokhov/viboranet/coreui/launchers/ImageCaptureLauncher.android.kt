package stepan.gorokhov.viboranet.coreui.launchers

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import stepan.gorokhov.viboranet.coreui.models.StableImage
import java.io.ByteArrayOutputStream
import java.io.InputStream

@Composable
actual fun rememberImageCaptureLauncher(
    onSuccess: (StableImage) -> Unit,
    onCancel: () -> Unit
): Launcher {
    val context = LocalContext.current
    val contentResolver: ContentResolver = context.contentResolver
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                val bytes = getBitmapFromUri(uri, contentResolver)?.toByteArray(COMPRESSION_QUALITY)
                bytes?.let {
                    onSuccess(StableImage(it))
                    return@rememberLauncherForActivityResult
                }
            }
            onCancel()
        }
    return remember {
        Launcher {
            galleryLauncher.launch(
                PickVisualMediaRequest(
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        }
    }
}

internal const val COMPRESSION_QUALITY = 70

fun getBitmapFromUri(uri: Uri, contentResolver: ContentResolver): Bitmap? {
    val inputStream: InputStream?
    try {
        inputStream = contentResolver.openInputStream(uri)
        val s = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()
        return s
    } catch (e: Exception) {
        Log.d("getBitmapFromUri", e.message ?: "")
        return null
    }
}

fun Bitmap.toByteArray(quality: Int): ByteArray {
    val outputStream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
    return outputStream.toByteArray()
}