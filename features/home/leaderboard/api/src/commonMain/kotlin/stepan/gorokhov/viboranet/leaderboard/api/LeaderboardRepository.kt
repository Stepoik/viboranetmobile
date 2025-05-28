package stepan.gorokhov.viboranet.leaderboard.api

import stepan.gorokhov.viboranet.leaderboard.api.models.LeaderBoardItem

interface LeaderboardRepository {
    suspend fun getLeaderBoard(): Result<List<LeaderBoardItem>>
}