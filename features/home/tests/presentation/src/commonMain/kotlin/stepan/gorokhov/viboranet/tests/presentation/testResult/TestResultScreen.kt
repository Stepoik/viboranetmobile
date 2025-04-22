package stepan.gorokhov.viboranet.tests.presentation.testResult

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold

@Composable
fun TestResultScreen(navController: NavController) {
    BaseScaffold {
        Text("Результат")
    }
}