package stepan.gorokhov.viboranet.coreui.routing

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun NavOptionsBuilder.popUpGraph() {
    popUpTo(0) {
        inclusive = true
    }
}