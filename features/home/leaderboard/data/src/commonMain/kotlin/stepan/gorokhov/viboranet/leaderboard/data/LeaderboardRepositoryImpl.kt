package stepan.gorokhov.viboranet.leaderboard.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import stepan.gorokhov.viboranet.coredata.network.NetworkConstants
import stepan.gorokhov.viboranet.leaderboard.api.LeaderboardRepository
import stepan.gorokhov.viboranet.leaderboard.api.models.LeaderBoardItem
import stepan.gorokhov.viboranet.leaderboard.data.models.LeaderboardItemDto

private const val BASE_URL = "${NetworkConstants.BASE_URL}/leaderboard"

class LeaderboardRepositoryImpl(
    private val httpClient: HttpClient
) : LeaderboardRepository {
    override suspend fun getLeaderBoard(): Result<List<LeaderBoardItem>> {
        return runCatching {
            httpClient.get(BASE_URL).body<List<LeaderboardItemDto>>().map { it.toDomain() }
        }
    }
}