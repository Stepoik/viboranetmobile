package stepan.gorokhov.viboranet.tests.presentation.testpreview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import stepan.gorokhov.viboranet.coreui.models.ErrorMessage
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel
import stepan.gorokhov.viboranet.tests.api.models.Vote
import stepan.gorokhov.viboranet.tests.api.repositories.TestRepository
import stepan.gorokhov.viboranet.tests.presentation.TestsRoutesArguments
import viboranet.features.home.tests.presentation.generated.resources.Res
import viboranet.features.home.tests.presentation.generated.resources.error_loading_test

class TestPreviewViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val previewRepository: TestRepository
) : BaseViewModel<TestPreviewState, TestPreviewViewModelState, TestPreviewEffect, TestPreviewEvent>() {
    private val testId = savedStateHandle[TestsRoutesArguments.ID] ?: ""

    override fun getInitialState() = TestPreviewViewModelState()

    override fun handleEvent(event: TestPreviewEvent) {
        viewModelScope.launch {
            when (event) {
                TestPreviewEvent.LoadPreview -> loadTestPreview()

                TestPreviewEvent.StartButtonClicked -> navigateStart()

                TestPreviewEvent.LikeClicked -> rateTest(vote = Vote.POSITIVE)
                TestPreviewEvent.DislikeClicked -> rateTest(vote = Vote.NEGATIVE)
            }
        }
    }

    private suspend fun loadTestPreview() {
        _state.update { it.copy(loading = true) }
        val result = previewRepository.getTestPreview(testId)

        result.onSuccess { preview ->
            _state.update { it.copy(preview = preview) }
        }
            .onFailure {
                _state.update { it.copy(error = getString(Res.string.error_loading_test)) }
            }

        _state.update { it.copy(loading = false) }
    }

    private suspend fun navigateStart() {
        _effect.emit(TestPreviewEffect.NavigateTest(testId))
    }

    private suspend fun rateTest(vote: Vote) {
        previewRepository.voteForTest(id = testId, vote = vote).onSuccess {
            loadTestPreview()
        }
    }
}