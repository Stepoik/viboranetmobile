package stepan.gorokhov.viboranet.tests.presentation.search

import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel
import stepan.gorokhov.viboranet.tests.api.repositories.TestRepository
import stepan.gorokhov.viboranet.tests.presentation.main.TestPreviewVO
import stepan.gorokhov.viboranet.tests.presentation.main.toVO

class SearchViewModel(
    private val testRepository: TestRepository
) : BaseViewModel<SearchViewModelState, SearchViewModelState, SearchEffect, SearchEvent>() {

    private var searchJob: Job? = null
        set(value) {
            field?.cancel()
            field = value
        }

    override fun getInitialState() = SearchViewModelState()

    override fun handleEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.QueryChanged -> {
                searchJob = viewModelScope.launch {
                    _state.update { it.copy(searchQuery = event.query) }
                    delay(300) // Debounce
                    searchTests(event.query, reset = true)
                }
            }

            is SearchEvent.TestClicked -> {
                _effect.tryEmit(SearchEffect.NavigateTestPreview(event.id))
            }

            is SearchEvent.StartTestClicked -> {
                _effect.tryEmit(SearchEffect.NavigateTest(event.id))
            }

            is SearchEvent.LoadMore -> {
                if (!_state.value.isLoadingMore && _state.value.hasMore) {
                    viewModelScope.launch(Dispatchers.Default) {
                        searchTests(_state.value.searchQuery, reset = false)
                    }
                }
            }

            is SearchEvent.NavigateBack -> _effect.tryEmit(SearchEffect.NavigateBack)
        }
    }

    private suspend fun searchTests(query: String, reset: Boolean) {
        if (query.isBlank()) {
            _state.update { it.copy(tests = persistentListOf()) }
            return
        }

        if (reset) {
            _state.update { it.copy(loading = true, tests = persistentListOf()) }
        } else {
            _state.update { it.copy(isLoadingMore = true) }
        }

        testRepository.searchTests(
            query,
            offset = if (reset) 0 else _state.value.tests.size.toLong()
        )
            .onSuccess { tests ->
                _state.update {
                    it.copy(
                        tests = if (reset) {
                            tests.map { test -> test.toVO() }
                        } else {
                            (it.tests + tests.map { test -> test.toVO() })
                        }.toImmutableList(),
                        loading = false,
                        isLoadingMore = false,
                        error = null,
                        hasMore = tests.isNotEmpty()
                    )
                }
            }
            .onFailure {
                _state.update {
                    it.copy(
                        error = "Ошибка при поиске тестов",
                        loading = false,
                        isLoadingMore = false
                    )
                }
            }
    }
} 