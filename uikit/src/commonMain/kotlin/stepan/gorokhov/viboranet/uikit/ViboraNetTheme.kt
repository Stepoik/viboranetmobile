package stepan.gorokhov.viboranet.uikit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Composable
fun ViboraNetTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalColors provides lightColors,
        content = content
    )
}

data object ViboraNetTheme {
    val colors: Colors
        @Composable
        get() = LocalColors.current
}

private val lightColors = Colors(
    primary = Color.Blue
)

data class Colors(
    val primary: Color,
)

private val LocalColors = staticCompositionLocalOf<Colors> { error("No default values") }