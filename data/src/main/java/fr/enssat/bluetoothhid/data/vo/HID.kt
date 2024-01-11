package fr.enssat.bluetoothhid.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HID(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    val id = 0
}
