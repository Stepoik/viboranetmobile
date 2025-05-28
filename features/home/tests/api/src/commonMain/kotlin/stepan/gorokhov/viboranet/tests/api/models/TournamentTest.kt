package stepan.gorokhov.viboranet.tests.api.models

data class TournamentTest(
    val id: String,
    val title: String,
    val description: String,
    val author: TestAuthor,
    val tags: List<String>,
    val imageId: String,
    val options: List<TournamentTestOption>
)
