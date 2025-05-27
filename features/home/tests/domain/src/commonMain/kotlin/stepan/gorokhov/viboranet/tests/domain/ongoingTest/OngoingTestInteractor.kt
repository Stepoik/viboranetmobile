package stepan.gorokhov.viboranet.tests.domain.ongoingTest

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import stepan.gorokhov.viboranet.tests.api.models.TournamentResult
import stepan.gorokhov.viboranet.tests.api.models.TournamentStage
import stepan.gorokhov.viboranet.tests.api.models.TournamentTest
import stepan.gorokhov.viboranet.tests.api.models.TournamentTestOption
import stepan.gorokhov.viboranet.tests.api.repositories.TestRepository
import stepan.gorokhov.viboranet.tests.api.repositories.TestResultRepository

class OngoingTestInteractor(
    private val testRepository: TestRepository,
    private val resultRepository: TestResultRepository
) {
    private val _state =
        MutableStateFlow<OngoingTestInteractorState>(OngoingTestInteractorState.Idle)
    val state = _state.asStateFlow()

    suspend fun startTest(id: String): Result<Any?> {
        if (state.value !is OngoingTestInteractorState.Idle)
            return Result.failure(IllegalStateException())

        val test = testRepository.getTournamentTest(id).getOrElse { return Result.failure(it) }
        startTest(test)
        return Result.success(Unit)
    }

    suspend fun pickOption(index: Int): Result<Any?> {
        val state = _state.value
        if (state !is OngoingTestInteractorState.Started)
            return Result.failure(IllegalStateException())

        val options = state.currentOptions
        val pickedOption = when (index) {
            options.first.index -> options.first
            options.second.index -> options.second
            else -> return Result.failure(IllegalStateException())
        }

        val currentStage = state.stages[state.stageIndex]
        val updatedStage =
            currentStage.copy(pickedOptions = currentStage.pickedOptions + pickedOption)

        val updatedStages = state.stages.toMutableList()
        updatedStages[state.stageIndex] = updatedStage
        if (state.choiceIndex + 1 == state.choiceCount) {
            val newStageOptions = updatedStage.pickedOptions.shuffled()
            updatedStages.add(TournamentStage(options = newStageOptions, pickedOptions = listOf()))

            if (updatedStage.pickedOptions.size == 1) {
                finish(updatedStages)
                return Result.success(Unit)
            }
            _state.update {
                state.copy(
                    stages = updatedStages,
                    currentOptions = newStageOptions.getOptionsPair(0),
                    choiceIndex = 0,
                    choiceCount = newStageOptions.size / 2,
                    stageIndex = state.stageIndex + 1
                )
            }
        } else {
            _state.update {
                state.copy(
                    stages = updatedStages,
                    choiceIndex = state.choiceIndex + 1,
                    currentOptions = updatedStage.options.getOptionsPair(state.choiceIndex + 1)
                )
            }
        }
        saveTestState()
        return Result.success(Unit)
    }

    private suspend fun startTest(test: TournamentTest) {
        val shuffledOptions = test.options.shuffled()
        val stages = listOf(TournamentStage(options = shuffledOptions, pickedOptions = listOf()))
        _state.update {
            OngoingTestInteractorState.Started(
                tournamentTest = test,
                stages = stages,
                currentOptions = shuffledOptions.getOptionsPair(0),
                choiceIndex = 0,
                choiceCount = shuffledOptions.size / 2,
                stageIndex = 0,
                stageCount = shuffledOptions.size.countTrailingZeroBits()
            )
        }
        saveTestState()
    }

    private suspend fun saveTestState() {

    }

    private suspend fun finish(stages: List<TournamentStage>) {
        val state = _state.value
        if (state !is OngoingTestInteractorState.Started) return

        _state.update { OngoingTestInteractorState.Finishing }

        val tournamentResult = state.toNewResult(stages)
        val saveResult = resultRepository.saveTournamentResult(result = tournamentResult)
        saveResult.onFailure {
            _state.update { state }
        }.onSuccess { resultId ->
            _state.update { OngoingTestInteractorState.Finished(resultId) }
        }
    }
}

private fun List<TournamentTestOption>.getOptionsPair(index: Int): Pair<TournamentTestOption, TournamentTestOption> {
    return get(index * 2) to get(index * 2 + 1)
}