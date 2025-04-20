package stepan.gorokhov.viboranet.uikit

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ViboraNetTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (isDarkTheme) {
        DarkColorScheme
    } else LightColorScheme
    MaterialTheme(colorScheme = colors, typography = Typography, content = content)
}

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF636AE8),       // основной синий
    onPrimary = Color.White,           // текст на синем
    primaryContainer = Color(0xFFBBDEFB),

    secondary = Color(0xFF26A69A),     // бирюзовый
    onSecondary = Color.White,

    background = Color.White,     // светлый фон
    onBackground = Color.Black,  // основной текст

    surface = Color.White,
    onSurface = Color(0xFF212121),

    error = Color(0xFFD32F2F),          // красный для ошибок
    onError = Color.White,

    outline = Color(0xFFB0BEC5),       // серый для границ
    surfaceVariant = Color(0xFFECEFF1), // карточки и контейнеры,

    tertiaryContainer = Color(0xFFF3F4F6),
    onTertiaryContainer = Color(0xFFBCC1CA),
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF90CAF9),       // осветлённый синий
    onPrimary = Color.Black,

    secondary = Color(0xFF80CBC4),     // бирюзовый
    onSecondary = Color.Black,

    background = Color(0xFF121212),    // тёмный фон
    onBackground = Color(0xFFE0E0E0),  // светлый текст

    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE0E0E0),

    error = Color(0xFFEF9A9A),
    onError = Color.Black,

    outline = Color(0xFF37474F),
    surfaceVariant = Color(0xFF263238)
)

private val Typography = Typography(
    titleLarge = TextStyle(fontSize = 64.sp, fontWeight = FontWeight.Bold),
    titleMedium = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold),
    headlineMedium = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
    bodyMedium = TextStyle(fontSize = 16.sp),
    bodySmall = TextStyle(fontSize = 14.sp),
    labelMedium = TextStyle(fontSize = 12.sp)
)