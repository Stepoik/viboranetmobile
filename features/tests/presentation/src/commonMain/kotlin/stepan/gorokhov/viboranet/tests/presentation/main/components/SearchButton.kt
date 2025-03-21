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

@Composable
fun SearchButton(onClick: () -> Unit) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        placeholder = { Text("Поиск теста...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        enabled = false,
        colors = OutlinedTextFieldDefaults.colors(
            disabledTextColor = Color.Unspecified,
            disabledBorderColor = MaterialTheme.colorScheme.outline,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}