package stepan.gorokhov.viboranet.tests.api.models

data class TournamentResult(
    val id: String,
    val userId: String,
    val options: List<TournamentResultOption>
)

data class TournamentResultOption(
    val index: Int,
    val title: String,
    val image: String,
    val localScore: Long,
    val globalScore: Long
)
