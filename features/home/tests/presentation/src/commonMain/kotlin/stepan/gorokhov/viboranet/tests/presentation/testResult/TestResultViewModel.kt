package stepan.gorokhov.viboranet.tests.presentation.testResult

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel
import stepan.gorokhov.viboranet.tests.api.repositories.TestResultRepository
import stepan.gorokhov.viboranet.tests.presentation.TestsRoutesArguments

class TestResultViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val resultRepository: TestResultRepository
) : BaseViewModel<TestResultState, TestResultViewModelState, TestResultEffect, TestResultEvent>() {

    private val resultId = savedStateHandle[TestsRoutesArguments.ID] ?: ""

    override fun getInitialState() = TestResultViewModelState()

    override fun handleEvent(event: TestResultEvent) {
        viewModelScope.launch {
            when (event) {
                TestResultEvent.LoadResult -> loadResult()
                TestResultEvent.NavigateBackClicked -> _effect.emit(TestResultEffect.NavigateBack)
            }
        }
    }

    private suspend fun loadResult() {
        _state.update { it.copy(isLoading = true) }
        val result = resultRepository.getResultById(resultId)

        result.onSuccess { tournamentResult ->
            _state.update { it.copy(result = tournamentResult) }
        }
        _state.update { it.copy(isLoading = false) }
    }
} 