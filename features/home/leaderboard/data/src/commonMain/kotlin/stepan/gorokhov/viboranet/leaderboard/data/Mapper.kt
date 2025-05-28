package stepan.gorokhov.viboranet.leaderboard.data

import stepan.gorokhov.viboranet.leaderboard.api.models.LeaderBoardItem
import stepan.gorokhov.viboranet.leaderboard.data.models.LeaderboardItemDto

fun LeaderboardItemDto.toDomain(): LeaderBoardItem {
    return LeaderBoardItem(
        userId = userId,
        username = username,
        image = imageUrl
    )
}