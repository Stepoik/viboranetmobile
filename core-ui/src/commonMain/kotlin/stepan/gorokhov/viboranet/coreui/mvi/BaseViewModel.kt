package stepan.gorokhov.viboranet.coreui.mvi

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import stepan.gorokhov.viboranet.core.flow.mapState

@Stable
fun interface EventHandler<Event : UIEvent> {
    fun handleEvent(event: Event)
}

abstract class BaseViewModel<ViewState : UIState, State : ViewModelState<ViewState>, Effect : UIEffect, Event : UIEvent> :
    ViewModel(),
    EventHandler<Event> {

    protected val _state = MutableStateFlow(getInitialState())
    val state = _state.mapState { it.toScreenState() } // UI Thread mapping

    protected val _effect = MutableSharedFlow<Effect>(extraBufferCapacity = 1)
    val effect: SharedFlow<Effect> = _effect

    protected abstract fun getInitialState(): State
}