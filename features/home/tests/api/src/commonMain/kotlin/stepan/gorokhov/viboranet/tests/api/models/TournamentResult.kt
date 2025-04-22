package stepan.gorokhov.viboranet.tests.api.models

data class TournamentResult(
    val testId: String,
    val stages: List<TournamentStage>
)
