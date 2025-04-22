package stepan.gorokhov.viboranet.tests.api.models

data class TournamentTest(
    val id: String,
    val title: String,
    val author: TestAuthor,
    val options: List<TournamentTestOption>
)
