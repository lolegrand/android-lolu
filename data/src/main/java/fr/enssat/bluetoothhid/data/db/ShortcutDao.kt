package fr.enssat.bluetoothhid.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import fr.enssat.bluetoothhid.data.vo.Shortcut

@Dao
interface ShortcutDao {

    // Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(shortcut: Shortcut)

    // Delete
    @Delete
    suspend fun delete(shortcut: Shortcut)

}