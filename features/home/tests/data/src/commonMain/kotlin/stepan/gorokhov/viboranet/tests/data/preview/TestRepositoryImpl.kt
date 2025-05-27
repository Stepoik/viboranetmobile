package stepan.gorokhov.viboranet.tests.data.preview

import stepan.gorokhov.viboranet.tests.api.models.CreateTournamentTest
import stepan.gorokhov.viboranet.tests.api.models.TestPreview
import stepan.gorokhov.viboranet.tests.api.models.TournamentTest
import stepan.gorokhov.viboranet.tests.api.models.Vote
import stepan.gorokhov.viboranet.tests.api.repositories.TestRepository
import stepan.gorokhov.viboranet.tests.data.preview.mappers.toRequest
import stepan.gorokhov.viboranet.tests.data.preview.mappers.toTestPreview
import stepan.gorokhov.viboranet.tests.data.preview.mappers.toTournament
import stepan.gorokhov.viboranet.tests.data.preview.network.TestApi
import stepan.gorokhov.viboranet.tests.data.preview.network.models.VoteForTestRequest

class TestRepositoryImpl(
    private val testApi: TestApi
) : TestRepository {
    override suspend fun createTest(test: CreateTournamentTest): Result<String> {
        return runCatching {
            testApi.createTest(test.toRequest()).id
        }
    }

    override suspend fun getTestPreviews(offset: Long): Result<List<TestPreview>> {
        return runCatching {
            testApi.getTests(offset = offset, limit = BASE_LIMIT).map { it.toTestPreview() }
        }
    }

    override suspend fun getTestPreview(id: String): Result<TestPreview> {
        return runCatching {
            testApi.getTest(id).toTestPreview()
        }
    }

    override suspend fun searchTests(text: String): Result<List<TestPreview>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTournamentTest(id: String): Result<TournamentTest> {
        return runCatching {
            testApi.getTest(id).toTournament()
        }
    }

    override suspend fun voteForTest(id: String, vote: Vote): Result<Any?> {
        return runCatching {
            val request = VoteForTestRequest(localRating = vote.toInt())
            testApi.voteForTest(id = id, request = request)
        }
    }

    companion object {
        private const val BASE_LIMIT = 50L
    }
}