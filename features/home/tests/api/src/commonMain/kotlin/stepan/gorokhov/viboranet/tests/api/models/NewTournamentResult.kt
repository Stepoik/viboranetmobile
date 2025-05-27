package stepan.gorokhov.viboranet.tests.api.models

data class NewTournamentResult(
    val testId: String,
    val options: List<ResultOption>
)

data class ResultOption(
    val index: Int,
    val score: Int
)