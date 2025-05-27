package stepan.gorokhov.viboranet.tests.api.models

data class CreateTournamentTest(
    val title: String,
    val description: String,
    val imageId: String,
    val options: List<CreateTestOption>,
    val tags: List<String>
)

data class CreateTestOption(
    val index: Int,
    val title: String,
    val description: String,
    val imageId: String?
)
