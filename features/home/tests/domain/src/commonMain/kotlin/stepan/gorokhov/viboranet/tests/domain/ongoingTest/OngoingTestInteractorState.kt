package stepan.gorokhov.viboranet.tests.domain.ongoingTest

import stepan.gorokhov.viboranet.tests.api.models.TournamentStage
import stepan.gorokhov.viboranet.tests.api.models.TournamentTest
import stepan.gorokhov.viboranet.tests.api.models.TournamentTestOption

sealed class OngoingTestInteractorState {
    data object Idle : OngoingTestInteractorState()
    data object Loading : OngoingTestInteractorState()
    data class Started(
        val tournamentTest: TournamentTest,
        val stages: List<TournamentStage>,
        val currentOptions: Pair<TournamentTestOption, TournamentTestOption>,
        val choiceIndex: Int,
        val choiceCount: Int,
        val stageIndex: Int,
        val stageCount: Int
    ) : OngoingTestInteractorState()

    data object Finishing : OngoingTestInteractorState()
    data class Finished(val resultId: String) : OngoingTestInteractorState()
}