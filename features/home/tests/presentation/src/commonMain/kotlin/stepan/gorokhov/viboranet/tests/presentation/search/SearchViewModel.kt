package stepan.gorokhov.viboranet.tests.presentation.search

import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
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

    override fun getInitialState() = SearchViewModelState()

    override fun handleEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.QueryChanged -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    _state.update { it.copy(searchQuery = event.query) }
                    delay(300) // Debounce
                    searchTests(event.query)
                }
            }
            is SearchEvent.TestClicked -> {
                _effect.tryEmit(SearchEffect.NavigateTestPreview(event.id))
            }
            is SearchEvent.StartTestClicked -> {
                _effect.tryEmit(SearchEffect.NavigateTest(event.id))
            }
        }
    }

    private suspend fun searchTests(query: String) {
        if (query.isBlank()) {
            _state.update { it.copy(tests = persistentListOf()) }
            return
        }

        _state.update { it.copy(loading = true) }
        
        testRepository.searchTests(query)
            .onSuccess { tests ->
                _state.update { 
                    it.copy(
                        tests = tests.map { test ->
                            test.toVO()
                        }.toImmutableList(),
                        loading = false,
                        error = null
                    )
                }
            }
            .onFailure {
                _state.update { 
                    it.copy(
                        error = "Ошибка при поиске тестов",
                        loading = false
                    )
                }
            }
    }
} 