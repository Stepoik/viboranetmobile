package stepan.gorokhov.viboranet.uikit.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

fun LazyListScope.verticalSpacer(dp: Dp) {
    item {
        Spacer(Modifier.padding(top = dp))
    }
}

fun LazyListScope.horizontalSpacer(dp: Dp) {
    item {
        Spacer(Modifier.padding(start = dp))
    }
}