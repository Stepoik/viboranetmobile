package stepan.gorokhov.viboranet.tests.presentation.createTest

import stepan.gorokhov.viboranet.coreui.mvi.UIEvent

sealed class CreateTestEvent : UIEvent {
    data class UpdateTitle(val title: String) : CreateTestEvent()
    data class UpdateDescription(val description: String) : CreateTestEvent()
    data class AddTag(val tag: String) : CreateTestEvent()
    data class RemoveTag(val tag: String) : CreateTestEvent()
    data class UpdateTestImage(val imageUrl: String) : CreateTestEvent()
    data class AddAnswerOption(val answerOption: AnswerOption) : CreateTestEvent()
    data class UpdateAnswerOption(val index: Int, val answerOption: AnswerOption) : CreateTestEvent()
    data class RemoveAnswerOption(val index: Int) : CreateTestEvent()
    object CreateTest : CreateTestEvent()
} 