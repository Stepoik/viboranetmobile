package stepan.gorokhov.viboranet.coreui.models

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

actual fun ByteArray.toImageBitmap(): ImageBitmap {
    return BitmapFactory.decodeByteArray(this, 0, size)?.asImageBitmap() ?: ImageBitmap(0, 0)
}