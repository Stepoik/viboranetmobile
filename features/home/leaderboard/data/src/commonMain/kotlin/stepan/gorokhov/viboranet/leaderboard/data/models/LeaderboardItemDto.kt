package stepan.gorokhov.viboranet.leaderboard.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeaderboardItemDto(
    @SerialName("user_id")
    val userId: String,
    @SerialName("username")
    val username: String,
    @SerialName("image_url")
    val imageUrl: String
)
