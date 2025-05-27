package stepan.gorokhov.viboranet.tests.data.preview.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultDto(
    val id: String,
    @SerialName("user_id")
    val userId: String,
    val options: List<ResultOptionDto>
)

@Serializable
data class ResultOptionDto(
    val index: Int,
    val title: String,
    @SerialName("image_id")
    val imageId: String,
    @SerialName("local_score")
    val localScore: Long,
    @SerialName("global_score")
    val globalScore: Long
)
