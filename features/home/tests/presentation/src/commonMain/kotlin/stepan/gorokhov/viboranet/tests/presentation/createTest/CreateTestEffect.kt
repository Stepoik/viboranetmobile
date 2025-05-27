package stepan.gorokhov.viboranet.tests.presentation.createTest

import stepan.gorokhov.viboranet.coreui.mvi.UIEffect

sealed class CreateTestEffect : UIEffect {
    data object NavigateBack : CreateTestEffect()
    data class ShowError(val message: String) : CreateTestEffect()
} 