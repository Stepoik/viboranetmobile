package stepan.gorokhov.viboranet.tests.presentation.main

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.persistentListOf
import stepan.gorokhov.viboranet.tests.api.models.TestPreview
import stepan.gorokhov.viboranet.uikit.ViboraNetTheme

@Preview
@Composable
actual fun MainTestsScreenPreview() {
    val test = TestPreview(id = "", title = "Мега", description = "тест", image = "")
    val state = MainTestsState(
        recommended = persistentListOf(test, test, test),
        popular = persistentListOf(test, test, test)
    )
    ViboraNetTheme(isDarkTheme = true) {
        MainTestsScreen(state = state)
    }
}