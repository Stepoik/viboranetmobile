package stepan.gorokhov.viboranet.tests.data.preview.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SaveTestResultRequest(
    @SerialName("test_id")
    val testId: String,
    val options: List<TestResultOptionDto>
)

@Serializable
data class TestResultOptionDto(
    val index: Int,
    @SerialName("local_score")
    val localScore: Int
)
