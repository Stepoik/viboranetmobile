package stepan.gorokhov.viboranet.coreui.mvi

interface ViewModelState<State : UIState> {
    fun toScreenState(): State
}

interface UIState