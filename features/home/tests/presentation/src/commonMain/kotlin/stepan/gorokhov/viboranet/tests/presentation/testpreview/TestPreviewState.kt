package stepan.gorokhov.viboranet.tests.presentation.testpreview

import stepan.gorokhov.viboranet.coreui.models.ErrorMessage
import stepan.gorokhov.viboranet.coreui.mvi.UIEffect
import stepan.gorokhov.viboranet.coreui.mvi.UIEvent
import stepan.gorokhov.viboranet.coreui.mvi.UIState
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState
import stepan.gorokhov.viboranet.tests.api.models.TestPreview

data class TestPreviewViewModelState(
    val loading: Boolean = false,
    val preview: TestPreview? = null,
    val error: String? = null
) : ViewModelState<TestPreviewState> {
    override fun toScreenState(): TestPreviewState {
        return when {
            preview == null && loading -> TestPreviewState.Loading
            preview == null && error != null -> TestPreviewState.Error(error)
            preview == null -> TestPreviewState.Idle
            else -> TestPreviewState.TestPreviewLoaded(preview)
        }
    }
}

sealed class TestPreviewState : UIState {
    data object Idle : TestPreviewState()
    data object Loading : TestPreviewState()
    data class Error(val message: String) : TestPreviewState()
    data class TestPreviewLoaded(val preview: TestPreview) : TestPreviewState()
}

sealed class TestPreviewEvent : UIEvent {
    data object LoadPreview : TestPreviewEvent()
    data object StartButtonClicked : TestPreviewEvent()
    data object LikeClicked : TestPreviewEvent()
    data object DislikeClicked : TestPreviewEvent()
}

sealed class TestPreviewEffect : UIEffect {
    data class NavigateTest(val id: String) : TestPreviewEffect()
}