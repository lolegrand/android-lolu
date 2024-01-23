package fr.enssat.bluetoothhid.data.repository

import fr.enssat.bluetoothhid.data.db.HIDDao
import fr.enssat.bluetoothhid.data.vo.HID
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HIDRepository @Inject constructor(
    private val HIDDao: HIDDao
) {

    fun watchHDI(): Flow<List<HID>> = HIDDao.watchAll()

    suspend fun insertHID(hid: HID) {
        HIDDao.insert(hid)
    }

    suspend fun deleteHID(hid: HID) {
        HIDDao.delete(hid)
    }
}