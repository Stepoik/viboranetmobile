package stepan.gorokhov.viboranet.tests.presentation.createTest

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.viboranet.common.api.repositories.ImageRepository
import stepan.gorokhov.viboranet.coredata.network.NetworkConstants
import stepan.gorokhov.viboranet.coreui.models.StableImage
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel
import stepan.gorokhov.viboranet.coreui.validation.isFailed
import stepan.gorokhov.viboranet.tests.api.repositories.TestRepository

class CreateTestViewModel(
    private val testId: String,
    private val imageRepository: ImageRepository,
    private val testRepository: TestRepository
) : BaseViewModel<CreateTestState, CreateTestViewModelState, CreateTestEffect, CreateTestEvent>() {
    override fun getInitialState(): CreateTestViewModelState = CreateTestViewModelState()

    override fun handleEvent(event: CreateTestEvent) {
        viewModelScope.launch {
            when (event) {
                is CreateTestEvent.LoadTest -> loadTest()

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

                is CreateTestEvent.UpdateTestImage -> uploadImage(event.image)

                is CreateTestEvent.AddAnswerOption -> {
                    _state.update { it.copy(answerOptions = it.answerOptions + event.answerOption) }
                }

                is CreateTestEvent.UpdateAnswerOption -> updateOption(event)

                is CreateTestEvent.RemoveAnswerOption -> {
                    val newOptions = _state.value.answerOptions.toMutableList()
                    val index = newOptions.indexOfFirst { it.id == event.id }
                    if (index >= 0) {
                        newOptions.removeAt(index)
                    }
                    _state.update { it.copy(answerOptions = newOptions) }
                }

                is CreateTestEvent.CreateTest -> createTest()

                is CreateTestEvent.UpdateAnswerOptionImage -> updateOptionImage(event)
            }
        }
    }

    private suspend fun createTest() {
        if (_state.value.loading) return

        _state.update { it.copy(loading = true) }
        val state = _state.value
        if (state.validate().isFailed) return

        val newTest = state.toCreateTest()
        if (testId.isEmpty()) {
            testRepository.createTest(newTest)
        } else {
            testRepository.updateTest(testId, newTest)
        }.onSuccess {
            _effect.emit(CreateTestEffect.NavigateBack)
        }
        _state.update { it.copy(loading = false) }
    }

    private suspend fun uploadImage(image: StableImage) {
        imageRepository.uploadImage(image.bytes).onSuccess { imageId ->
            _state.update { it.copy(testImageId = imageId) }
        }
    }

    private suspend fun updateOption(event: CreateTestEvent.UpdateAnswerOption) {
        val newOptions = _state.value.answerOptions.toMutableList()
        val index = newOptions.indexOfFirst { it.id == event.answerOption.id }
        if (index >= 0) {
            newOptions[index] = event.answerOption
        }
        _state.update { it.copy(answerOptions = newOptions) }
    }

    private suspend fun updateOptionImage(event: CreateTestEvent.UpdateAnswerOptionImage) {
        val currentOption = _state.value.answerOptions.find { it.id == event.id }
        currentOption?.let {
            val imageId = imageRepository.uploadImage(event.image.bytes).getOrElse { return }
            val imageUrl = "${NetworkConstants.BASE_URL}/images/$imageId"
            val newOption = currentOption.copy(imageId = imageId, imageUrl = imageUrl)

            val newOptions = _state.value.answerOptions.toMutableList()
            val index = newOptions.indexOfFirst { it.id == event.id }
            if (index >= 0) {
                newOptions[index] = newOption
                _state.update { it.copy(answerOptions = newOptions) }
            }
        }
    }

    private suspend fun loadTest() {
        if (testId.isEmpty()) return

        testRepository.getTournamentTest(testId).onSuccess { test ->
            _state.update { test.toCreateTestViewModelState(it) }
        }.onFailure {
            _state.update { it.copy(error = "Не удалось загрузить тест")}
        }
    }
} 