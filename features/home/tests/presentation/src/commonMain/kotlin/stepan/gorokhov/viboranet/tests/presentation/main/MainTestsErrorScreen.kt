package stepan.gorokhov.viboranet.tests.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import stepan.gorokhov.viboranet.coreui.mvi.EventHandler
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainTestsErrorScreen(state: MainTestsState, eventHandler: EventHandler<MainTestsEvent>) {
    BaseScaffold {
        PullToRefreshBox(
            isRefreshing = state.refreshing,
            onRefresh = { eventHandler.handleEvent(MainTestsEvent.Refresh) },
            modifier = Modifier.fillMaxSize()
        ) {
            Box(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                Text("Error")
            }
        }
    }
}