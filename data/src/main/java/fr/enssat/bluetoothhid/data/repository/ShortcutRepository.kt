package fr.enssat.bluetoothhid.data.repository

import fr.enssat.bluetoothhid.data.db.ShortcutDao
import fr.enssat.bluetoothhid.data.vo.Shortcut
import javax.inject.Inject

class ShortcutRepository @Inject constructor(
    private val shortcutDao: ShortcutDao
) {

    suspend fun addShortcut(shortcut: Shortcut) {
        shortcutDao.insert(shortcut)
    }

    suspend fun deleteShortcut(shortcut: Shortcut) {
        shortcutDao.delete(shortcut)
    }

}