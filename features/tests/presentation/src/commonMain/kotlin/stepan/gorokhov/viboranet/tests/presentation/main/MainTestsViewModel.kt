package stepan.gorokhov.viboranet.tests.presentation.main

import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import stepan.gorokhov.viboranet.coreui.models.ErrorMessage
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel
import stepan.gorokhov.viboranet.tests.api.repositories.TestPreviewRepository
import viboranet.features.tests.presentation.generated.resources.Res
import viboranet.features.tests.presentation.generated.resources.error_loading_tests

class MainTestsViewModel(
    private val previewRepository: TestPreviewRepository
) : BaseViewModel<MainTestsState, MainTestsState, MainTestsEffect, MainTestsEvent>() {
    override fun getInitialState(): MainTestsState {
        return MainTestsState()
    }

    override fun sendEvent(event: MainTestsEvent) {
        viewModelScope.launch {
            when (event) {
                MainTestsEvent.LoadTests -> loadTests()
                MainTestsEvent.Refresh -> refreshTests()
                is MainTestsEvent.TestClicked -> selectTest(event.id)
            }
        }
    }

    private suspend fun loadTests() {
        if (_state.value.loading || _state.value.refreshing) return

        _state.update { it.copy(loading = true) }
        updateTests()
        _state.update { it.copy(loading = false) }
    }

    private suspend fun refreshTests() {
        if (_state.value.loading || _state.value.refreshing) return

        _state.update { it.copy(refreshing = true) }
        updateTests()
        _state.update { it.copy(refreshing = false) }
    }

    private suspend fun updateTests() {
        val result = previewRepository.fetchTestPreviews()
        result.onSuccess { previews ->
            _state.update { it.copy(recommended = previews.toImmutableList()) }
        }
            .onFailure {
                _state.update { it.copy(error = ErrorMessage(getString(Res.string.error_loading_tests))) }
            }
    }

    private suspend fun selectTest(id: String) {
        _effect.send(MainTestsEffect.NavigateTest(id))
    }
}