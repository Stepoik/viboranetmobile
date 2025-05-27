package stepan.gorokhov.viboranet.tests.presentation.createTest

import stepan.gorokhov.viboranet.coreui.models.StableImage
import stepan.gorokhov.viboranet.coreui.mvi.UIEvent

sealed class CreateTestEvent : UIEvent {
    data class UpdateTitle(val title: String) : CreateTestEvent()
    data class UpdateDescription(val description: String) : CreateTestEvent()
    data class AddTag(val tag: String) : CreateTestEvent()
    data class RemoveTag(val tag: String) : CreateTestEvent()
    data class UpdateTestImage(val image: StableImage) : CreateTestEvent()
    data class AddAnswerOption(val answerOption: AnswerOption) : CreateTestEvent()
    data class UpdateAnswerOption(val answerOption: AnswerOption) : CreateTestEvent()
    data class UpdateAnswerOptionImage(val id: String, val image: StableImage) : CreateTestEvent()
    data class RemoveAnswerOption(val id: String) : CreateTestEvent()
    data object CreateTest : CreateTestEvent()
} 