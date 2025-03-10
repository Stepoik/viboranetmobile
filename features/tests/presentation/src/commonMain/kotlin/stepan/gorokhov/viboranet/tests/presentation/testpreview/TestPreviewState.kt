package stepan.gorokhov.viboranet.tests.presentation.testpreview

import stepan.gorokhov.viboranet.coreui.models.ErrorMessage
import stepan.gorokhov.viboranet.coreui.mvi.UIEffect
import stepan.gorokhov.viboranet.coreui.mvi.UIEvent
import stepan.gorokhov.viboranet.coreui.mvi.UIState
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState
import stepan.gorokhov.viboranet.tests.api.models.TestPreview

data class TestPreviewViewModelState(
    val isLoading: Boolean = false,
    val preview: TestPreview? = null,
    val errorMessage: ErrorMessage? = null
) : ViewModelState<TestPreviewState> {
    override fun toScreenState(): TestPreviewState {
        return when {
            isLoading -> {
                TestPreviewState.Loading
            }

            preview == null -> {
                TestPreviewState.Idle
            }

            errorMessage != null -> {
                TestPreviewState.Error(errorMessage)
            }

            else -> {
                TestPreviewState.TestPreviewLoaded(preview)
            }
        }
    }
}

sealed class TestPreviewState : UIState {
    data object Idle : TestPreviewState()

    data object Loading : TestPreviewState()

    data class Error(val errorMessage: ErrorMessage) : TestPreviewState()

    data class TestPreviewLoaded(val preview: TestPreview) : TestPreviewState()
}

sealed class TestPreviewEffect : UIEffect {
    data class NavigateTest(val id: String) : TestPreviewEffect()
}

sealed class TestPreviewEvent : UIEvent {
    data object LoadPreview : TestPreviewEvent()
    data object StartButtonClicked : TestPreviewEvent()
}