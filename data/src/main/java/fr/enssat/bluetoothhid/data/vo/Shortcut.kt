package fr.enssat.bluetoothhid.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Shortcut(
    val name: String,
    val icon: String,
    val color: String
) {
    @PrimaryKey(autoGenerate = true)
    val id = 0
}