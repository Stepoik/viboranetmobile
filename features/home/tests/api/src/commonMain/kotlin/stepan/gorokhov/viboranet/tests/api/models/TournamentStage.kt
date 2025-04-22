package stepan.gorokhov.viboranet.tests.api.models

data class TournamentStage(
    val options: List<TournamentTestOption>,
    val pickedOptions: List<TournamentTestOption>
)