package fr.enssat.bluetoothhid.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.enssat.bluetoothhid.data.db.HIDDao
import fr.enssat.bluetoothhid.data.db.LoLuDB
import fr.enssat.bluetoothhid.data.db.ShortcutDao

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    fun hidDao(loLuDB: LoLuDB): HIDDao = loLuDB.hidDao()

    @Provides
    fun shortcutDao(loLuDB: LoLuDB): ShortcutDao = loLuDB.shortcutDao()

}