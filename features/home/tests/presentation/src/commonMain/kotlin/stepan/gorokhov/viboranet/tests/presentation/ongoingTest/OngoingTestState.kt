package stepan.gorokhov.viboranet.tests.presentation.ongoingTest

import stepan.gorokhov.viboranet.coreui.mvi.UIEffect
import stepan.gorokhov.viboranet.coreui.mvi.UIEvent
import stepan.gorokhov.viboranet.coreui.mvi.UIState

sealed class OngoingTestState(
) : UIState {
    data object Loading : OngoingTestState()
    data class Started(
        val currentStage: Int,
        val stageCount: Int,
        val currentChoiceIndex: Int,
        val choiceCount: Int,
        val currentChoice: Pair<TestOption, TestOption>,
        val pickedOptionId: String?
    )
}

data class TestOption(
    val id: String,
    val image: String,
    val title: String
)

sealed class OngoingTestEvent : UIEvent

sealed class OngoingTestEffect : UIEffect