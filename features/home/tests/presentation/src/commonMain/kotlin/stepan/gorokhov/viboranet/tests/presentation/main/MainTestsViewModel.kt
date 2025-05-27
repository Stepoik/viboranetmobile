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

    override fun handleEvent(event: MainTestsEvent) {
        viewModelScope.launch {
            when (event) {
                MainTestsEvent.LoadTests -> updateTests(force = false)
                MainTestsEvent.Refresh -> updateTests(force = true)
                is MainTestsEvent.TestClicked -> navigateToPreview(event.id)
                is MainTestsEvent.StartTestClicked -> navigateToTest(event.id)
                MainTestsEvent.SearchClicked -> navigateToSearch()
                MainTestsEvent.CreateTestClicked -> navigateToCreateTest()
                is MainTestsEvent.SearchQueryChanged -> updateSearchQuery(event.query)
            }
        }
    }

    private suspend fun updateTests(force: Boolean = false) {
        if (!force && (_state.value.loading || _state.value.refreshing)) return

        _state.update { it.copy(refreshing = true) }
        val offset = if (force) 0L else _state.value.tests.size.toLong()
        val result = testRepository.getTestPreviews(offset)
        result.onSuccess { previews ->
            _state.update {
                val newTests = previews.map { it.toVO() }
                if (force) {
                    it.copy(tests = newTests.toImmutableList())
                } else {
                    it.copy(tests = (it.tests + newTests).toImmutableList())
                }
            }
        }.onFailure {
            _state.update {
                it.copy(
                    error = ErrorMessage(getString(Res.string.error_loading_tests))
                )
            }
        }
        _state.update { it.copy(refreshing = false)}
    }

    private suspend fun navigateToPreview(id: String) {
        _effect.emit(MainTestsEffect.NavigateTestPreview(id))
    }

    private suspend fun navigateToTest(id: String) {
        _effect.emit(MainTestsEffect.NavigateTest(id))
    }

    private suspend fun navigateToSearch() {
        _effect.emit(MainTestsEffect.NavigateSearch)
    }

    private suspend fun navigateToCreateTest() {
        _effect.emit(MainTestsEffect.NavigateCreateTest)
    }

    private fun updateSearchQuery(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }
}