package fr.enssat.bluetoothhid.data.vo

import androidx.room.Embedded
import androidx.room.Relation

data class HIDWithShortcuts (
    @Embedded val hid: HID,
    @Relation(
        parentColumn = "id",
        entityColumn = "hidID"
    )
    val shortcuts: List<Shortcut>
)