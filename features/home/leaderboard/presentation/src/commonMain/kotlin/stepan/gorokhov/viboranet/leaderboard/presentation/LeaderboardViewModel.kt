package stepan.gorokhov.viboranet.leaderboard.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import stepan.gorokhov.viboranet.coreui.mvi.BaseViewModel
import stepan.gorokhov.viboranet.leaderboard.api.LeaderboardRepository

class LeaderboardViewModel(
    private val leaderboardRepository: LeaderboardRepository
) : BaseViewModel<LeaderboardViewModelState, LeaderboardViewModelState, LeaderboardEffect, LeaderboardEvent>() {

    override fun getInitialState() = LeaderboardViewModelState()

    override fun handleEvent(event: LeaderboardEvent) {
        when (event) {
            is LeaderboardEvent.LoadLeaderboard -> loadLeaderboard()
            is LeaderboardEvent.Refresh -> loadLeaderboard()
            is LeaderboardEvent.NavigateBack -> _effect.tryEmit(LeaderboardEffect.NavigateBack)
        }
    }

    private fun loadLeaderboard() {
        if (_state.value.loading) return

        viewModelScope.launch {
            _state.update { it.copy(loading = true) }

            leaderboardRepository.getLeaderBoard().onSuccess { leaderboard ->
                _state.update {
                    it.copy(
                        leaderboard = leaderboard.toImmutableList(),
                        loading = false,
                        error = null
                    )
                }
            }.onFailure {
                _state.update {
                    it.copy(
                        error = "Ошибка при загрузке таблицы лидеров",
                        loading = false
                    )
                }
            }
        }
    }
} 