package stepan.gorokhov.viboranet.tests.api.repositories

import stepan.gorokhov.viboranet.tests.api.models.TestPreview
import stepan.gorokhov.viboranet.tests.api.models.TournamentResult
import stepan.gorokhov.viboranet.tests.api.models.TournamentTest

interface TestRepository {
    suspend fun getTestPreviews(): Result<List<TestPreview>>

    suspend fun getTestPreview(id: String): Result<TestPreview>

    suspend fun getTournamentTest(id: String): Result<TournamentTest>

    // Возращает id зафиксированного результата
    suspend fun saveTournamentResult(result: TournamentResult): Result<String>
}