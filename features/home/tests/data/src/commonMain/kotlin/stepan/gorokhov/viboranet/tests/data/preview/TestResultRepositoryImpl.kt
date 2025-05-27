package stepan.gorokhov.viboranet.tests.data.preview

import stepan.gorokhov.viboranet.tests.api.models.NewTournamentResult
import stepan.gorokhov.viboranet.tests.api.models.TournamentResult
import stepan.gorokhov.viboranet.tests.api.repositories.TestResultRepository
import stepan.gorokhov.viboranet.tests.data.preview.mappers.toDomain
import stepan.gorokhov.viboranet.tests.data.preview.mappers.toRequest
import stepan.gorokhov.viboranet.tests.data.preview.network.ResultApi

class TestResultRepositoryImpl(
    private val resultApi: ResultApi
) : TestResultRepository {
    override suspend fun saveTournamentResult(result: NewTournamentResult): Result<String> {
        return runCatching {
            val request = result.toRequest()
            resultApi.saveResult(request).id
        }
    }

    override suspend fun getResultById(id: String): Result<TournamentResult> {
        return runCatching {
            resultApi.getResult(id).toDomain()
        }
    }
}