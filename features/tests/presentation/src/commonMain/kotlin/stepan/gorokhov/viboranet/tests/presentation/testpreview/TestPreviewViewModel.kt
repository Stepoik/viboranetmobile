package stepan.gorokhov.viboranet.tests.presentation.testpreview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import stepan.gorokhov.viboranet.coreui.models.ErrorMessage
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel
import stepan.gorokhov.viboranet.tests.api.repositories.TestPreviewRepository
import stepan.gorokhov.viboranet.tests.presentation.TestsRoutesArguments
import viboranet.features.tests.presentation.generated.resources.Res
import viboranet.features.tests.presentation.generated.resources.error_loading_test

class TestPreviewViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val previewRepository: TestPreviewRepository
) : BaseViewModel<TestPreviewState, TestPreviewViewModelState, TestPreviewEffect, TestPreviewEvent>() {
    private val testId = savedStateHandle[TestsRoutesArguments.ID] ?: ""

    override fun getInitialState() = TestPreviewViewModelState()

    override fun sendEvent(event: TestPreviewEvent) {
        viewModelScope.launch {
            when (event) {
                TestPreviewEvent.LoadPreview -> loadTestPreview()

                TestPreviewEvent.StartButtonClicked -> navigateStart()
            }
        }
    }

    private suspend fun loadTestPreview() {
        _state.update { it.copy(isLoading = true) }
        val result = previewRepository.fetchTestPreview(testId)

        result.onSuccess { preview ->
            _state.update { it.copy(preview = preview) }
        }
        .onFailure {
            _state.update { it.copy(errorMessage = ErrorMessage(message = getString(Res.string.error_loading_test))) }
        }

        _state.update { it.copy(isLoading = false) }
    }

    private suspend fun navigateStart() {
        _effect.send(TestPreviewEffect.NavigateTest(testId))
    }
}