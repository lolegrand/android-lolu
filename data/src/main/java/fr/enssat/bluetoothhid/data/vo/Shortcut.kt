package fr.enssat.bluetoothhid.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Shortcut(
    val hidID: Int,
    val icon: String,
    val backgroundRed: Float,
    val backgroundGreen: Float,
    val backgroundBlue: Float,
    val iconRed: Float,
    val iconGreen: Float,
    val iconBlue: Float,
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}