package stepan.gorokhov.viboranet.coreui.launchers

import androidx.compose.runtime.Composable
import stepan.gorokhov.viboranet.coreui.models.StableImage

@Composable
expect fun rememberImageCaptureLauncher(
    onSuccess: (StableImage) -> Unit,
    onCancel: () -> Unit
): Launcher