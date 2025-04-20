package stepan.gorokhov.viboranet.tests.api.repositories

import stepan.gorokhov.viboranet.tests.api.models.TestPreview

interface TestPreviewRepository {
    suspend fun getTestPreviews(): Result<List<TestPreview>>

    suspend fun getTestPreview(id: String): Result<TestPreview>
}