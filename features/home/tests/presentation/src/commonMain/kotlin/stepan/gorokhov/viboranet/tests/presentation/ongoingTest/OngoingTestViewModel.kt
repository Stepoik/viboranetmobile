package stepan.gorokhov.viboranet.tests.presentation.ongoingTest

import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState

class OngoingTestViewModel :
    BaseViewModel<OngoingTestState, OngoingTestState, OngoingTestEffect, OngoingTestEvent>() {
    override fun sendEvent(event: OngoingTestEvent) {

    }

    override fun getInitialState(): OngoingTestState {
        TODO("Not yet implemented")
    }
}

private data class OngoingTestViewModelState(
    val isLoading: Boolean = false,
    val currentStage: Int = 0,
    val stageCount: Int = 0,
    val currentChoiceIndex: Int = 0,
    val choiceCount: Int = 0,
    val currentChoice: Pair<TestOption, TestOption>? = null,
    val pickedOptionId: String? = null
) : ViewModelState<OngoingTestState> {
    override fun toScreenState(): OngoingTestState {
        when {
            isLoading -> OngoingTestState.Loading

        }
    }
}