package fr.enssat.bluetoothhid.lolu.navigation.destination.impl

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import fr.enssat.bluetoothhid.lolu.navigation.destination.AbstractArgs
import fr.enssat.bluetoothhid.lolu.navigation.destination.AbstractDestination

object HIDDetail : AbstractDestination<HIDDetail.HIDDetailArgs>("HIDDetail") {
    class HIDDetailArgs(val hidID: Int) : AbstractArgs

    const val HID_ID = "HID_ID"

    override val route: String =
        "${routeType}/{${HID_ID}}"

    override fun createRoute(args: HIDDetailArgs): String =
        "${routeType}/${args.hidID}"

    override fun args(): List<NamedNavArgument> =
        listOf(
            navArgument(HID_ID) {
                type = NavType.IntType
            }
        )
}