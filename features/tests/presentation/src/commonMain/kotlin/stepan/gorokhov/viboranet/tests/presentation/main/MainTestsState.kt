package stepan.gorokhov.viboranet.tests.presentation.main

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import stepan.gorokhov.viboranet.coreui.models.ErrorMessage
import stepan.gorokhov.viboranet.coreui.mvi.UIEffect
import stepan.gorokhov.viboranet.coreui.mvi.UIEvent
import stepan.gorokhov.viboranet.coreui.mvi.UIState
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState
import stepan.gorokhov.viboranet.tests.api.models.TestPreview

data class MainTestsState(
    val loading: Boolean = false,
    val refreshing: Boolean = false,
    val recommended: ImmutableList<TestPreview> = persistentListOf(),
    val popular: ImmutableList<TestPreview> = persistentListOf(),
    val error: ErrorMessage? = null
) : ViewModelState<MainTestsState>, UIState {
    override fun toScreenState(): MainTestsState {
        return this
    }
}

sealed class MainTestsEvent : UIEvent {
    data object LoadTests : MainTestsEvent()
    data object Refresh : MainTestsEvent()
    data class TestClicked(val id: String) : MainTestsEvent()
}

sealed class MainTestsEffect : UIEffect {
    data class NavigateTest(val id: String) : MainTestsEffect()
}
