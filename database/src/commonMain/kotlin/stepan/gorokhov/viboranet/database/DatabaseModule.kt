package stepan.gorokhov.viboranet.database

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect fun platformModule(): Module

val databaseModule = module {
    single(createdAtStart = true) { getAppDatabase(get()) }
    single { get<AppDatabase>().userDao() }

    includes(platformModule())
}

fun getAppDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        .setQueryCoroutineContext(Dispatchers.IO)
        .setDriver(BundledSQLiteDriver())
        .build()
}