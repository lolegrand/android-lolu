package fr.enssat.bluetoothhid.lolu.navigation.destination

import androidx.navigation.NamedNavArgument

abstract class AbstractDestination<T : AbstractArgs>(val routeType: String) {
    open val route: String
        get() = routeType

    open fun createRoute(args: T): String =
        routeType

    open fun args(): List<NamedNavArgument> =
        listOf()
}