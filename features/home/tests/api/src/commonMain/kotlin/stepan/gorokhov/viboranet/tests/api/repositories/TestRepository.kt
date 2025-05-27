package stepan.gorokhov.viboranet.tests.api.repositories

import stepan.gorokhov.viboranet.tests.api.models.CreateTournamentTest
import stepan.gorokhov.viboranet.tests.api.models.TestPreview
import stepan.gorokhov.viboranet.tests.api.models.TournamentResult
import stepan.gorokhov.viboranet.tests.api.models.TournamentTest
import stepan.gorokhov.viboranet.tests.api.models.Vote

interface TestRepository {
    suspend fun createTest(test: CreateTournamentTest): Result<String>

    suspend fun getTestPreviews(offset: Long): Result<List<TestPreview>>

    suspend fun getTestPreview(id: String): Result<TestPreview>

    suspend fun searchTests(text: String): Result<List<TestPreview>>

    suspend fun getTournamentTest(id: String): Result<TournamentTest>

    suspend fun voteForTest(id: String, vote: Vote): Result<Any?>
}