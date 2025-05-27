package stepan.gorokhov.viboranet.tests.data.preview.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SaveResultRequest(
    @SerialName("test_id")
    val testId: String,
    val options: List<SaveResultOption>
)

@Serializable
data class SaveResultOption(
    val index: Int,
    @SerialName("local_score")
    val localScore: Int
)
