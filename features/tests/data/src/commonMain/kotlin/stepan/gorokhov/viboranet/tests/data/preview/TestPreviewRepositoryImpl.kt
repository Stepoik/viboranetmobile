package stepan.gorokhov.viboranet.tests.data.preview

import stepan.gorokhov.viboranet.tests.api.models.TestPreview
import stepan.gorokhov.viboranet.tests.api.repositories.TestPreviewRepository

class TestPreviewRepositoryImpl(
    private val
) : TestPreviewRepository {
    override suspend fun fetchTestPreviews(): Result<List<TestPreview>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchTestPreview(id: String): Result<TestPreview> {
        TODO("Not yet implemented")
    }
}