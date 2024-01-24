package fr.enssat.bluetoothhid.data.repository

import fr.enssat.bluetoothhid.data.db.HIDDao
import fr.enssat.bluetoothhid.data.vo.HID
import fr.enssat.bluetoothhid.data.vo.HIDWithShortcuts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HIDRepository @Inject constructor(
    private val HIDDao: HIDDao
) {

    fun watchHID(): Flow<List<HID>> = HIDDao.watchAll()

    fun watchHIDWithShortcutsById(id: Int): Flow<HIDWithShortcuts> = HIDDao.watchByIDWithShortcut(id)

    suspend fun insertHID(hid: HID) {
        HIDDao.insert(hid)
    }

    suspend fun deleteHID(hid: HID) {
        HIDDao.delete(hid)
    }
}