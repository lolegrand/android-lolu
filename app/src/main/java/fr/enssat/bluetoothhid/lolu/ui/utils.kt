package fr.enssat.bluetoothhid.lolu.ui

import android.view.KeyEvent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ThumbUp

// Some of the material icons
val materialIcons = mapOf(
    "AccountBox" to Icons.Default.AccountBox,
    "Add" to Icons.Default.Add,
    "AccountCircle" to Icons.Default.AccountCircle,
    "AddCircle" to Icons.Default.AddCircle,
    "ArrowBack" to Icons.Default.ArrowBack,
    "ArrowDropDown" to Icons.Default.ArrowDropDown,
    "ArrowForward" to Icons.Default.ArrowForward,
    "Build" to Icons.Default.Build,
    "Call" to Icons.Default.Call,
    "Check" to Icons.Default.Check,
    "CheckCircle" to Icons.Default.CheckCircle,
    "Clear" to Icons.Default.Clear,
    "Close" to Icons.Default.Close,
    "Create" to Icons.Default.Create,
    "DateRange" to Icons.Default.DateRange,
    "Face" to Icons.Default.Face,
    "ThumbUp" to Icons.Default.ThumbUp,
    "Phone" to Icons.Default.Phone,
    "Place" to Icons.Default.Place,
    "KeyboardArrowDown" to Icons.Default.KeyboardArrowDown,
    "KeyboardArrowUp" to Icons.Default.KeyboardArrowUp,
    "KeyboardArrowRight" to Icons.Default.KeyboardArrowRight,
    "KeyboardArrowLeft" to Icons.Default.KeyboardArrowLeft
)

val KeyEventMap = mapOf(
    KeyEvent.KEYCODE_A to 4,
    KeyEvent.KEYCODE_B to 5,
    KeyEvent.KEYCODE_C to 6,
    KeyEvent.KEYCODE_D to 7,
    KeyEvent.KEYCODE_E to 8,
    KeyEvent.KEYCODE_F to 9,
    KeyEvent.KEYCODE_G to 10,
    KeyEvent.KEYCODE_H to 11,
    KeyEvent.KEYCODE_I to 12,
    KeyEvent.KEYCODE_J to 13,
    KeyEvent.KEYCODE_K to 14,
    KeyEvent.KEYCODE_L to 15,
    KeyEvent.KEYCODE_M to 16,
    KeyEvent.KEYCODE_N to 17,
    KeyEvent.KEYCODE_O to 18,
    KeyEvent.KEYCODE_P to 19,
    KeyEvent.KEYCODE_Q to 20,
    KeyEvent.KEYCODE_R to 21,
    KeyEvent.KEYCODE_S to 22,
    KeyEvent.KEYCODE_T to 23,
    KeyEvent.KEYCODE_U to 24,
    KeyEvent.KEYCODE_V to 25,
    KeyEvent.KEYCODE_W to 26,
    KeyEvent.KEYCODE_X to 27,
    KeyEvent.KEYCODE_Y to 28,
    KeyEvent.KEYCODE_Z to 29,


    KeyEvent.KEYCODE_1 to 30,
    KeyEvent.KEYCODE_2 to 31,
    KeyEvent.KEYCODE_3 to 32,
    KeyEvent.KEYCODE_4 to 33,
    KeyEvent.KEYCODE_5 to 34,
    KeyEvent.KEYCODE_6 to 35,
    KeyEvent.KEYCODE_7 to 36,
    KeyEvent.KEYCODE_8 to 37,
    KeyEvent.KEYCODE_9 to 38,
    KeyEvent.KEYCODE_0 to 39,

    KeyEvent.KEYCODE_F1 to 58,
    KeyEvent.KEYCODE_F2 to 59,
    KeyEvent.KEYCODE_F3 to 60,
    KeyEvent.KEYCODE_F4 to 61,
    KeyEvent.KEYCODE_F5 to 62,
    KeyEvent.KEYCODE_F6 to 63,
    KeyEvent.KEYCODE_F7 to 64,
    KeyEvent.KEYCODE_F8 to 65,
    KeyEvent.KEYCODE_F9 to 66,
    KeyEvent.KEYCODE_F10 to 67,
    KeyEvent.KEYCODE_F11 to 68,
    KeyEvent.KEYCODE_F12 to 69,

    KeyEvent.KEYCODE_ENTER to 40,
    KeyEvent.KEYCODE_ESCAPE to 41,
    KeyEvent.KEYCODE_DEL to 42,
    KeyEvent.KEYCODE_TAB to 43,
    KeyEvent.KEYCODE_SPACE to 44,
    KeyEvent.KEYCODE_MINUS to 45,
    KeyEvent.KEYCODE_EQUALS to 46,
    KeyEvent.KEYCODE_LEFT_BRACKET to 47,
    KeyEvent.KEYCODE_RIGHT_BRACKET to 48,
    KeyEvent.KEYCODE_BACKSLASH to 49,
    KeyEvent.KEYCODE_POUND to 50,
    KeyEvent.KEYCODE_SEMICOLON to 51,
    KeyEvent.KEYCODE_APOSTROPHE to 52,
    KeyEvent.KEYCODE_GRAVE to 53,
    KeyEvent.KEYCODE_COMMA to 54,
    KeyEvent.KEYCODE_PERIOD to 55,
    KeyEvent.KEYCODE_SLASH to 56,

    KeyEvent.KEYCODE_SCROLL_LOCK to 71,
    KeyEvent.KEYCODE_INSERT to 73,
    KeyEvent.KEYCODE_HOME to 74,
    KeyEvent.KEYCODE_PAGE_UP to 75,
    KeyEvent.KEYCODE_FORWARD_DEL to 76,
    KeyEvent.KEYCODE_MOVE_END to 77,
    KeyEvent.KEYCODE_PAGE_DOWN to 78,
    KeyEvent.KEYCODE_NUM_LOCK to 83,

    KeyEvent.KEYCODE_DPAD_RIGHT to 79,
    KeyEvent.KEYCODE_DPAD_LEFT to 80,
    KeyEvent.KEYCODE_DPAD_DOWN to 81,
    KeyEvent.KEYCODE_DPAD_UP to 82,

    // special key for FR MAC keyboard '@', spend a while to figure it out
    KeyEvent.KEYCODE_AT to 100
)
