package stepan.gorokhov.viboranet.tests.presentation.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold

@Composable
internal fun MainTestsLoadingSkeleton() {
    BaseScaffold {
        Text("Loading")
    }
}