package stepan.gorokhov.viboranet.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import stepan.gorokhov.viboranet.uikit.ViboraNetTheme

@Composable
fun BaseScaffold(
    topBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(topBar = topBar, floatingActionButton = floatingActionButton) {
        Column(Modifier.background(MaterialTheme.colorScheme.background).padding(it)) {
            content()
        }
    }
}