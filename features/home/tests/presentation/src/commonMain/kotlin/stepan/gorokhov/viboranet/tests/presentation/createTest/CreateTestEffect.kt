package stepan.gorokhov.viboranet.tests.presentation.createTest

import stepan.gorokhov.viboranet.coreui.mvi.UIEffect

sealed class CreateTestEffect : UIEffect {
    data class NavigateBack(val testId: String) : CreateTestEffect()
    data class ShowError(val message: String) : CreateTestEffect()
} 