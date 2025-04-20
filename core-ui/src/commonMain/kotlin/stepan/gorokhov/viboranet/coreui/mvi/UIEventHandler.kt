package stepan.gorokhov.viboranet.coreui.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Stable
class UIEventHandler<Event : UIEvent>(
    private val eventHandler: EventHandler<Event>
) : EventHandler<Event> {
    override fun sendEvent(event: Event) {
        eventHandler.sendEvent(event)
    }
}

@Composable
fun <Event : UIEvent> rememberUIEventHandler(eventHandler: EventHandler<Event>): UIEventHandler<Event> {
    return remember(eventHandler) {
        UIEventHandler(eventHandler)
    }
}