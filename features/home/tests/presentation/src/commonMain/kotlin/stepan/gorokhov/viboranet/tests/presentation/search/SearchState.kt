package stepan.gorokhov.viboranet.tests.presentation.search

import stepan.gorokhov.viboranet.coreui.mvi.UIEffect
import stepan.gorokhov.viboranet.coreui.mvi.UIEvent
import stepan.gorokhov.viboranet.coreui.mvi.UIState
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState
import stepan.gorokhov.viboranet.tests.presentation.main.TestPreviewVO
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class SearchViewModelState(
    val loading: Boolean = false,
    val searchQuery: String = "",
    val tests: ImmutableList<TestPreviewVO> = persistentListOf(),
    val error: String? = null,
    val hasMore: Boolean = true,
    val isLoadingMore: Boolean = false
) : UIState, ViewModelState<SearchViewModelState> {
    override fun toScreenState() = this
}

sealed class SearchEvent : UIEvent {
    data class QueryChanged(val query: String) : SearchEvent()
    data class TestClicked(val id: String) : SearchEvent()
    data class StartTestClicked(val id: String) : SearchEvent()
    data object LoadMore : SearchEvent()
    data object NavigateBack : SearchEvent()
}

sealed class SearchEffect : UIEffect {
    data class NavigateTest(val id: String) : SearchEffect()
    data class NavigateTestPreview(val id: String) : SearchEffect()
    data object NavigateBack : SearchEffect()
}