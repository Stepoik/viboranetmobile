package stepan.gorokhov.viboranet.common.data.network

import kotlinx.serialization.Serializable

@Serializable
data class UploadImageResponse(
    val imageId: String
)
