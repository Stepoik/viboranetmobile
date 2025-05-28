package stepan.gorokhov.viboranet.leaderboard.presentation.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import stepan.gorokhov.viboranet.coredata.di.AUTHORIZED_KTOR_CLIENT_QUALIFIER
import stepan.gorokhov.viboranet.leaderboard.api.LeaderboardRepository
import stepan.gorokhov.viboranet.leaderboard.data.LeaderboardRepositoryImpl
import stepan.gorokhov.viboranet.leaderboard.presentation.LeaderboardViewModel

val leaderboardModule = module {
    single<LeaderboardRepository> { LeaderboardRepositoryImpl(get(AUTHORIZED_KTOR_CLIENT_QUALIFIER)) }

    viewModel { LeaderboardViewModel(get()) }
}