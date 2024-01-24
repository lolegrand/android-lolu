package fr.enssat.bluetoothhid.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.enssat.bluetoothhid.data.vo.HID
import fr.enssat.bluetoothhid.data.vo.Shortcut

@Database(
    entities = [
        HID::class,
        Shortcut::class
    ],
    version = 2
)
abstract class LoLuDB : RoomDatabase() {

    abstract fun hidDao(): HIDDao

    abstract fun shortcutDao(): ShortcutDao

    companion object {
        fun databaseBuilder(context: Context) =
            Room.databaseBuilder(context.applicationContext, LoLuDB::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()

        private const val DATABASE_NAME = "lolu.db"
    }
}