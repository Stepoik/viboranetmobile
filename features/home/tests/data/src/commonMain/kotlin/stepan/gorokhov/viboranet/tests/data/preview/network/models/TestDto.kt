package stepan.gorokhov.viboranet.tests.data.preview.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TestDto(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("image_id")
    val imageId: String,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("global_rating")
    val globalRating: Long,
    @SerialName("local_rating")
    val localRating: Long,
    @SerialName("options")
    val options: List<TestOptionDto>,
    @SerialName("author")
    val author: TestAuthorDto
)

@Serializable
data class TestOptionDto(
    @SerialName("index")
    val index: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("image_id")
    val imageId: String,
    @SerialName("global_score")
    val globalScore: Long
)

@Serializable
data class TestAuthorDto(
    @SerialName("user_id")
    val userId: String,
    @SerialName("username")
    val username: String,
    @SerialName("image_url")
    val imageUrl: String
)
