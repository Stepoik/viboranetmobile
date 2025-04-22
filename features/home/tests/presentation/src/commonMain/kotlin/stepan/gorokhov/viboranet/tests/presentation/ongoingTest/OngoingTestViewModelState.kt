package stepan.gorokhov.viboranet.tests.presentation.ongoingTest

import stepan.gorokhov.viboranet.coreui.models.ErrorMessage
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState
import stepan.gorokhov.viboranet.tests.api.models.TournamentTestOption
import stepan.gorokhov.viboranet.tests.domain.ongoingTest.OngoingTestInteractorState

internal data class OngoingTestViewModelState(
    val interactorState: OngoingTestInteractorState? = null,
    val pickedOptionId: String? = null,
    val error: ErrorMessage? = null
) : ViewModelState<OngoingTestState> {
    override fun toScreenState(): OngoingTestState {
        return when {
            interactorState == null || interactorState is OngoingTestInteractorState.Idle
                    || interactorState is OngoingTestInteractorState.Loading -> {
                OngoingTestState.Loading
            }

            interactorState is OngoingTestInteractorState.Started -> {
                OngoingTestState.Started(
                    testTitle = interactorState.tournamentTest.title,
                    currentStage = interactorState.stageIndex + 1,
                    stageCount = interactorState.stageCount,
                    currentChoiceIndex = interactorState.choiceIndex + 1,
                    choiceCount = interactorState.choiceCount,
                    currentChoice = interactorState.currentOptions.toVO(),
                    pickedOptionId = pickedOptionId
                )
            }

            else -> OngoingTestState.Finishing
        }
    }
}

private fun Pair<TournamentTestOption, TournamentTestOption>.toVO(): Pair<TestOptionVO, TestOptionVO> {
    return first.toVO() to second.toVO()
}

private fun TournamentTestOption.toVO(): TestOptionVO {
    return TestOptionVO(
        id = id,
        image = image,
        title = title
    )
}