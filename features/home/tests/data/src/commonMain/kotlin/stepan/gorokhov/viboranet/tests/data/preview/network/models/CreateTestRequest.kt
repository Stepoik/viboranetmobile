package stepan.gorokhov.viboranet.tests.data.preview.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateTestRequest(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("image_id")
    val imageId: String,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("options")
    val options: List<NewOptionDto>
)

@Serializable
data class NewOptionDto(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("image_id")
    val imageId: String?
)
