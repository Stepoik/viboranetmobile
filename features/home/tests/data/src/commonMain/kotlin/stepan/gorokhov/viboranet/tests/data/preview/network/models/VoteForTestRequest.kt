package stepan.gorokhov.viboranet.tests.data.preview.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoteForTestRequest(
    @SerialName("local_rating")
    val localRating: Int
)
