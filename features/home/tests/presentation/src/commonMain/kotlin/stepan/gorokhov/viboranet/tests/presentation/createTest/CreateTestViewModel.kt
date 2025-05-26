package stepan.gorokhov.viboranet.tests.presentation.createTest

import kotlinx.coroutines.flow.update
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel

class CreateTestViewModel : BaseViewModel<CreateTestState, CreateTestViewModelState, CreateTestEffect, CreateTestEvent>() {

    override fun getInitialState(): CreateTestViewModelState = CreateTestViewModelState()

    override fun handleEvent(event: CreateTestEvent) {
        when (event) {
            is CreateTestEvent.UpdateTitle -> {
                _state.update { it.copy(title = event.title) }
            }
            is CreateTestEvent.UpdateDescription -> {
                _state.update { it.copy(description = event.description) }
            }
            is CreateTestEvent.AddTag -> {
                _state.update { it.copy(tags = it.tags + event.tag) }
            }
            is CreateTestEvent.RemoveTag -> {
                _state.update { it.copy(tags = it.tags - event.tag) }
            }
            is CreateTestEvent.UpdateTestImage -> {
                _state.update { it.copy(testImage = event.imageUrl) }
            }
            is CreateTestEvent.AddAnswerOption -> {
                _state.update { it.copy(answerOptions = it.answerOptions + event.answerOption) }
            }
            is CreateTestEvent.UpdateAnswerOption -> {
                val newOptions = _state.value.answerOptions.toMutableList()
                newOptions[event.index] = event.answerOption
                _state.update { it.copy(answerOptions = newOptions) }
            }
            is CreateTestEvent.RemoveAnswerOption -> {
                val newOptions = _state.value.answerOptions.toMutableList()
                newOptions.removeAt(event.index)
                _state.update { it.copy(answerOptions = newOptions) }
            }
            is CreateTestEvent.CreateTest -> {
                // Здесь должна быть логика создания теста
                // Например, отправка данных на сервер
                // После успешного создания:
                // _effect.emit(CreateTestEffect.NavigateBack(testId))
                // При ошибке:
                // _effect.emit(CreateTestEffect.ShowError(errorMessage))
            }
        }
    }
} 