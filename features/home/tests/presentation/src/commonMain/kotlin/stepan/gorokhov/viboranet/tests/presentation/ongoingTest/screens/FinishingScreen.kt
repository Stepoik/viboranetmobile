package stepan.gorokhov.viboranet.tests.presentation.ongoingTest.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold

private const val DOTS_DELAY = 100L

@Composable
fun FinishingScreen() {
    var dotsCount by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(Unit) {
        while (isActive) {
            delay(DOTS_DELAY)
            dotsCount = (dotsCount + 1) % 4
        }
    }
    BaseScaffold {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Завершаем${".".repeat(dotsCount)}")
        }
    }
}