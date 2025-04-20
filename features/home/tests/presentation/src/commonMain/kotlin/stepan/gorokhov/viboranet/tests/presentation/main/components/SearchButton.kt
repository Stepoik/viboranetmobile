package stepan.gorokhov.viboranet.tests.presentation.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import stepan.gorokhov.viboranet.uikit.components.TextFieldWithIcon

@Composable
fun SearchButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    TextFieldWithIcon(
        icon = Icons.Default.Search,
        value = "",
        onValueChanged = {},
        enabled = false,
        placeholder = "Поиск теста...",
        modifier = modifier.clickable(onClick = onClick)
    )
}