package stepan.gorokhov.viboranet.tests.presentation.main

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.persistentListOf
import stepan.gorokhov.viboranet.coreui.mvi.EventHandler
import stepan.gorokhov.viboranet.tests.api.models.TestAuthor
import stepan.gorokhov.viboranet.tests.api.models.TestPreview
import stepan.gorokhov.viboranet.uikit.ViboraNetTheme

@Preview
@Composable
actual fun MainTestsScreenPreview() {
    val test = TestPreviewVO(
        id = "",
        title = "Мега",
        description = "тест",
        image = "",
        author = TestAuthorVO("", "")
    )
    val state = MainTestsState(
        tests = persistentListOf(test)
    )
    ViboraNetTheme(isDarkTheme = true) {
        MainTestsScreen(state = state, eventHandler = EventHandler { })
    }
}