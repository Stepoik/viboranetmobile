package stepan.gorokhov.viboranet.tests.api.repositories

import stepan.gorokhov.viboranet.tests.api.models.NewTournamentResult
import stepan.gorokhov.viboranet.tests.api.models.TournamentResult

interface TestResultRepository {
    // Возращает id зафиксированного результата
    suspend fun saveTournamentResult(result: NewTournamentResult): Result<String>

    suspend fun getResultById(id: String): Result<TournamentResult>
}