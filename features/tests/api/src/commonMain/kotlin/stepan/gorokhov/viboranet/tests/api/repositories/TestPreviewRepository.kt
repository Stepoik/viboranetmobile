package stepan.gorokhov.viboranet.tests.api.repositories

import stepan.gorokhov.viboranet.tests.api.models.TestPreview

interface TestPreviewRepository {
    suspend fun fetchTestPreviews(): Result<List<TestPreview>>

    suspend fun fetchTestPreview(id: String): Result<TestPreview>
}