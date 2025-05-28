package stepan.gorokhov.viboranet.leaderboard.presentation

import stepan.gorokhov.viboranet.coreui.mvi.UIEffect
import stepan.gorokhov.viboranet.coreui.mvi.UIEvent
import stepan.gorokhov.viboranet.coreui.mvi.UIState
import stepan.gorokhov.viboranet.coreui.mvi.ViewModelState
import stepan.gorokhov.viboranet.leaderboard.api.models.LeaderBoardItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class LeaderboardViewModelState(
    val loading: Boolean = false,
    val leaderboard: ImmutableList<LeaderBoardItem> = persistentListOf(),
    val error: String? = null
) : UIState, ViewModelState<LeaderboardViewModelState> {
    override fun toScreenState() = this
}

sealed class LeaderboardEvent : UIEvent {
    data object LoadLeaderboard : LeaderboardEvent()
    data object Refresh : LeaderboardEvent()
    data object NavigateBack : LeaderboardEvent()
}

sealed class LeaderboardEffect : UIEffect {
    data object NavigateBack : LeaderboardEffect()
} 