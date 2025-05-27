package stepan.gorokhov.viboranet.tests.presentation.createTest

import stepan.gorokhov.viboranet.core.uuid.Uuid
import stepan.gorokhov.viboranet.coredata.network.NetworkConstants
import stepan.gorokhov.viboranet.coreui.mvi.UIState
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState

data class CreateTestState(
    val title: String = "",
    val description: String = "",
    val tags: List<String> = emptyList(),
    val testImage: String? = null,
    val answerOptions: List<AnswerOption> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
) : UIState

data class CreateTestViewModelState(
    val title: String = "",
    val description: String = "",
    val tags: List<String> = emptyList(),
    val testImageId: String? = null,
    val answerOptions: List<AnswerOption> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
) : ViewModelState<CreateTestState> {
    override fun toScreenState(): CreateTestState = CreateTestState(
        title = title,
        description = description,
        tags = tags,
        testImage = testImageId?.let { "${NetworkConstants.BASE_URL}/images/$it" },
        answerOptions = answerOptions,
        loading = loading,
        error = error
    )
}

data class AnswerOption(
    val id: String = Uuid.generate(),
    val title: String,
    val description: String,
    val imageId: String?,
    val imageUrl: String?,
)

fun AnswerOption(): AnswerOption {
    return AnswerOption(
        title = "",
        description = "",
        imageId = null,
        imageUrl = null
    )
}
