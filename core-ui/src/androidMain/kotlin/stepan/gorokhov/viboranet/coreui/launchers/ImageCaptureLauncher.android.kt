package stepan.gorokhov.viboranet.coreui.launchers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import stepan.gorokhov.viboranet.coreui.models.StableImage
import java.io.File

@Composable
actual fun rememberImageCaptureLauncher(
    onSuccess: (StableImage) -> Unit,
    onCancel: () -> Unit
): Launcher {
    val context = LocalContext.current
    var imageFileState by remember { mutableStateOf<File?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri = imageFileState?.toUri() ?: run {
                onCancel()
                return@rememberLauncherForActivityResult
            }
            val inputStream = context.contentResolver.openInputStream(imageUri)
            val bytes = inputStream?.readBytes()
            if (bytes != null) {
                onSuccess(StableImage(bytes))
            } else onCancel()
        } else onCancel()
    }
    return remember(launcher, context) {
        Launcher {
            val imageFile = createImageFile(context)
            imageFileState = imageFile
            val imageUri = getFileUri(context = context, file = imageFile)
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            }
            launcher.launch(intent)
        }
    }
}

private const val BASE_FILE_NAME = "extra_image_file"

private fun createImageFile(context: Context): File {
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

    return File.createTempFile(
        BASE_FILE_NAME,
        ".jpg",
        storageDir
    )
}

private fun getFileUri(context: Context, file: File): Uri {
    return FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
}