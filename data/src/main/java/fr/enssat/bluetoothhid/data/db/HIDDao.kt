package fr.enssat.bluetoothhid.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import fr.enssat.bluetoothhid.data.vo.HID
import fr.enssat.bluetoothhid.data.vo.HIDWithShortcuts
import kotlinx.coroutines.flow.Flow

@Dao
interface HIDDao {

    // Watch
    @Query("SELECT * FROM HID")
    fun watchAll(): Flow<List<HID>>

    @Transaction
    @Query("SELECT * FROM HID WHERE id = :id LIMIT 1")
    fun watchByIDWithShortcut(id: Int): Flow<HIDWithShortcuts>

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hid: HID)

    // Delete
    @Delete
    suspend fun delete(hid: HID)
}