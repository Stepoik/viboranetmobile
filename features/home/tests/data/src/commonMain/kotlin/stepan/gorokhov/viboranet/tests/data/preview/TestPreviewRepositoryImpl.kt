package stepan.gorokhov.viboranet.tests.data.preview

import stepan.gorokhov.viboranet.tests.api.models.TestAuthor
import stepan.gorokhov.viboranet.tests.api.models.TestPreview
import stepan.gorokhov.viboranet.tests.api.repositories.TestPreviewRepository

class TestPreviewRepositoryImpl : TestPreviewRepository {
    override suspend fun getTestPreviews(): Result<List<TestPreview>> {
        return Result.success(
            listOf(
                TestPreview(
                    "1",
                    title = "Хайповый",
                    description = "231312",
                    image = "https://cs6.pikabu.ru/post_img/big/2015/06/08/3/1433735650_472905306.jpg",
                    author = TestAuthor(
                        "","Степан", image = ""
                    )
                )
            )
        )
    }

    override suspend fun getTestPreview(id: String): Result<TestPreview> {
        TODO("Not yet implemented")
    }
}