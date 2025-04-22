package stepan.gorokhov.viboranet.tests.presentation.main

import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import stepan.gorokhov.viboranet.coreui.models.ErrorMessage
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel
import stepan.gorokhov.viboranet.tests.api.repositories.TestRepository
import viboranet.features.home.tests.presentation.generated.resources.Res
import viboranet.features.home.tests.presentation.generated.resources.error_loading_tests

class MainTestsViewModel(
    private val testRepository: TestRepository
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
                MainTestsEvent.SearchClicked -> navigateSearch()
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
        val result = testRepository.getTestPreviews()
        result.onSuccess { previews ->
            _state.update { it.copy(tests = previews.map { it.toVO() }.toImmutableList()) }
        }.onFailure {
            _state.update { it.copy(error = ErrorMessage(getString(Res.string.error_loading_tests))) }
        }
    }

    private suspend fun selectTest(id: String) {
        _effect.emit(MainTestsEffect.NavigateTest(id))
    }

    private suspend fun navigateSearch() {
        _effect.emit(MainTestsEffect.NavigateSearch)
    }
}