package stepan.gorokhov.viboranet.tests.data.preview.network.models

import kotlinx.serialization.Serializable

@Serializable
data class VoteForTestRequest(
    val delta: Int
)
