package stepan.gorokhov.viboranet.coreui.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import stepan.gorokhov.viboranet.core.flow.mapState

abstract class BaseViewModel<ViewState : UIState, State : ViewModelState<ViewState>, Effect : UIEffect, Event : UIEvent> :
    ViewModel() {

    protected val _state = MutableStateFlow(getInitialState())
    val state = _state.mapState { it.toScreenState() } // UI Thread mapping

    protected val _effect = Channel<Effect?>()
    val effect: ReceiveChannel<Effect?> = _effect

    protected abstract fun getInitialState(): State

    abstract fun sendEvent(event: Event)
}