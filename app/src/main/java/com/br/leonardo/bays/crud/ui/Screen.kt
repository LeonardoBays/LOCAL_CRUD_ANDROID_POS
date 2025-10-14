package com.br.leonardo.bays.crud.ui

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object MatchScreen: Screen("match_screen")
    object ManagerMatchScreen: Screen("manager_match_screen")

    fun withArgs(vararg args: String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}