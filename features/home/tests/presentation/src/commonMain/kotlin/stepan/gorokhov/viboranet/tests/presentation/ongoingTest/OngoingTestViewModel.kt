package stepan.gorokhov.viboranet.tests.presentation.ongoingTest

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update
import org.jetbrains.compose.resources.getString
import stepan.gorokhov.viboranet.coreui.models.ErrorMessage
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState
import stepan.gorokhov.viboranet.tests.domain.ongoingTest.OngoingTestInteractor
import stepan.gorokhov.viboranet.tests.domain.ongoingTest.OngoingTestInteractorState
import stepan.gorokhov.viboranet.tests.presentation.TestsRoutesArguments
import viboranet.features.home.tests.presentation.generated.resources.Res
import viboranet.features.home.tests.presentation.generated.resources.error_loading_test

internal class OngoingTestViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val ongoingTestInteractor: OngoingTestInteractor
) : BaseViewModel<OngoingTestState, OngoingTestViewModelState, OngoingTestEffect, OngoingTestEvent>() {
    private val testId = savedStateHandle[TestsRoutesArguments.ID] ?: ""

    init {
        viewModelScope.launch {
            ongoingTestInteractor.state.collect { interactorState ->
                if (interactorState is OngoingTestInteractorState.Finished) {
                    navigateResult(interactorState.resultId)
                }
                _state.update { it.copy(interactorState = interactorState) }
            }
        }
    }

    override fun handleEvent(event: OngoingTestEvent) {
        viewModelScope.launch {
            when (event) {
                is OngoingTestEvent.StartTest -> startTest()
                is OngoingTestEvent.OptionPicked -> pickOption(event.id)
            }
        }
    }

    override fun getInitialState(): OngoingTestViewModelState {
        return OngoingTestViewModelState()
    }

    private suspend fun startTest() {
        ongoingTestInteractor.startTest(testId).onFailure {
            _state.update { it.copy(error = ErrorMessage(getString(Res.string.error_loading_test))) }
        }
    }

    private suspend fun pickOption(id: String) {
        ongoingTestInteractor.pickOption(id)
    }

    private suspend fun navigateResult(resultId: String) {
        _effect.emit(OngoingTestEffect.NavigateResult(resultId))
    }
}