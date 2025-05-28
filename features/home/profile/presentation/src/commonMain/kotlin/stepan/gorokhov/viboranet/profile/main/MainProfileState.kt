package stepan.gorokhov.viboranet.profile.main

import stepan.gorokhov.viboranet.coreui.mvi.UIState
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState
import stepan.gorokhov.viboranet.tests.api.models.TestPreview

data class MainProfileState(
    val username: String = "",
    val avatarUrl: String? = null,
    val loading: Boolean = false,
    val error: String? = null,
    val createdTests: List<TestPreview> = emptyList(),
    val isLoadingMore: Boolean = false,
    val hasMoreTests: Boolean = true
) : UIState

data class MainProfileViewModelState(
    val loading: Boolean = false,
    val username: String = "",
    val avatarUrl: String = "",
    val error: String? = null,
    val createdTests: List<TestPreview> = emptyList(),
    val isLoadingMore: Boolean = false,
    val hasMoreTests: Boolean = true,
    val currentPage: Int = 0
) : ViewModelState<MainProfileState> {
    override fun toScreenState(): MainProfileState = MainProfileState(
        username = username,
        avatarUrl = avatarUrl,
        loading = loading,
        error = error,
        createdTests = createdTests,
        isLoadingMore = isLoadingMore,
        hasMoreTests = hasMoreTests
    )
}