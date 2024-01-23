package fr.enssat.bluetoothhid.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.enssat.bluetoothhid.data.vo.HID
import kotlinx.coroutines.flow.Flow

@Dao
interface HIDDao {

    // Watch
    @Query("SELECT * FROM HID")
    fun watchAll(): Flow<List<HID>>

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hid: HID)

    // Delete
    @Delete
    suspend fun delete(hid: HID)
}