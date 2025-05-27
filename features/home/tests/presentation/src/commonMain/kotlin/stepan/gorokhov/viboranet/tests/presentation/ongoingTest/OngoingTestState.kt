package stepan.gorokhov.viboranet.tests.presentation.ongoingTest

import stepan.gorokhov.viboranet.coreui.mvi.UIEffect
import stepan.gorokhov.viboranet.coreui.mvi.UIEvent
import stepan.gorokhov.viboranet.coreui.mvi.UIState

sealed class OngoingTestState : UIState {
    data object Loading : OngoingTestState()
    data class Started(
        val testTitle: String,
        val currentStage: Int,
        val stageCount: Int,
        val currentChoiceIndex: Int,
        val choiceCount: Int,
        val currentChoice: Pair<TestOptionVO, TestOptionVO>,
        val pickedOptionId: String?
    ) : OngoingTestState()

    data object Finishing : OngoingTestState()
}

data class TestOptionVO(
    val index: Int,
    val image: String,
    val title: String
)

sealed class OngoingTestEvent : UIEvent {
    data object StartTest : OngoingTestEvent()
    data class OptionPicked(val index: Int) : OngoingTestEvent()
}

sealed class OngoingTestEffect : UIEffect {
    data class NavigateResult(val resultId: String): OngoingTestEffect()
}