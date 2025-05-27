package stepan.gorokhov.viboranet.tests.presentation.testResult

import stepan.gorokhov.viboranet.coreui.models.ErrorMessage
import stepan.gorokhov.viboranet.coreui.mvi.UIEffect
import stepan.gorokhov.viboranet.coreui.mvi.UIEvent
import stepan.gorokhov.viboranet.coreui.mvi.UIState
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState
import stepan.gorokhov.viboranet.tests.api.models.TournamentResult
import stepan.gorokhov.viboranet.tests.api.models.TournamentResultOption

data class TestResultViewModelState(
    val isLoading: Boolean = false,
    val result: TournamentResult? = null,
    val errorMessage: ErrorMessage? = null
) : ViewModelState<TestResultState> {
    override fun toScreenState(): TestResultState {
        return when {
            isLoading -> TestResultState.Loading
            result == null -> TestResultState.Idle
            errorMessage != null -> TestResultState.Error(errorMessage)
            else -> TestResultState.ResultLoaded(
                options = result.options.sortedByDescending { it.localScore }
            )
        }
    }
}

sealed class TestResultState : UIState {
    data object Idle : TestResultState()
    data object Loading : TestResultState()
    data class Error(val errorMessage: ErrorMessage) : TestResultState()
    data class ResultLoaded(val options: List<TournamentResultOption>) : TestResultState()
}

sealed class TestResultEvent : UIEvent {
    data object LoadResult : TestResultEvent()
    data object NavigateBackClicked : TestResultEvent()
}

sealed class TestResultEffect : UIEffect {
    data object NavigateBack : TestResultEffect()
} 