package stepan.gorokhov.viboranet.uikit.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun BaseScaffold(content: @Composable () -> Unit) {
    Scaffold {
        Column() {
            content()
        }
    }
}