package stepan.gorokhov.viboranet.tests.domain.ongoingTest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import stepan.gorokhov.viboranet.tests.api.models.NewTournamentResult
import stepan.gorokhov.viboranet.tests.api.models.ResultOption
import stepan.gorokhov.viboranet.tests.api.models.TournamentStage

suspend fun OngoingTestInteractorState.Started.toNewResult(
    stages: List<TournamentStage>
): NewTournamentResult = withContext(Dispatchers.Default) {
    val scores = IntArray(tournamentTest.options.size)
    for (stage in stages) {
        println(stage)
        for (option in stage.options) {
            scores[option.index]++
        }
    }
    NewTournamentResult(
        testId = tournamentTest.id,
        options = scores.toResultOption()
    )
}

fun IntArray.toResultOption(): List<ResultOption> {
    return mapIndexed { index, score ->
        ResultOption(index = index, score = score)
    }
}