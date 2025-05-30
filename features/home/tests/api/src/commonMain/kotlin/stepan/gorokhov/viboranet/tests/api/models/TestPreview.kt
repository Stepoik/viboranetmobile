package stepan.gorokhov.viboranet.tests.api.models

data class TestPreview(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val author: TestAuthor,
    val globalRating: Long,
    val localRating: Long
)
