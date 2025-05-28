package stepan.gorokhov.viboranet.home.flow

import org.koin.dsl.module
import stepan.gorokhov.viboranet.chat.chatModule
import stepan.gorokhov.viboranet.leaderboard.presentation.di.leaderboardModule
import stepan.gorokhov.viboranet.profile.di.profileModule
import stepan.gorokhov.viboranet.tests.presentation.di.testsModule

val homeModule = module {
    includes(testsModule, profileModule, chatModule, leaderboardModule)
}