package stepan.gorokhov.viboranet.coreui.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.ImageBitmap

@Immutable
data class StableImage(
    val bytes: ByteArray
) {
    val bitmap: ImageBitmap get() = bytes.toImageBitmap()

    // Равны только если по ссылке
    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }
}

expect fun ByteArray.toImageBitmap(): ImageBitmap