package stepan.gorokhov.viboranet.profile.main

import stepan.gorokhov.viboranet.coreui.mvi.UIState
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState

data class MainProfileState(
    val username: String = "",
    val avatarUrl: String? = null,
    val loading: Boolean = false,
    val error: String? = null
) : UIState

data class MainProfileViewModelState(
    val username: String = "",
    val avatarUrl: String? = null,
    val loading: Boolean = false,
    val error: String? = null
) : ViewModelState<MainProfileState> {
    override fun toScreenState(): MainProfileState = MainProfileState(
        username = username,
        avatarUrl = avatarUrl,
        loading = loading,
        error = error
    )
} 