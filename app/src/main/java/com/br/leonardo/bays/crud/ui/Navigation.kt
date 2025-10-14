package com.br.leonardo.bays.crud.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.br.leonardo.bays.crud.ui.home.HomeScreen
import com.br.leonardo.bays.crud.ui.manager_match.ManagerMatchScreen
import com.br.leonardo.bays.crud.ui.match.MatchScreen
import com.br.leonardo.bays.crud.viewmodel.home.HomeViewModel
import com.br.leonardo.bays.crud.viewmodel.manager_match.ManagerMatchViewModel
import com.br.leonardo.bays.crud.viewmodel.match.MatchViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(navController, viewModel)
        }
        composable(
            route = Screen.MatchScreen.route + "/{matchId}",
            arguments = listOf(
                navArgument("matchId") {
                    type = NavType.LongType
                }
            ),
        ) { entry ->
            val viewModel: MatchViewModel = hiltViewModel()
            val matchId = entry.arguments?.getLong("matchId") ?: 0
            viewModel.loadMatch(matchId)
            MatchScreen(navController, viewModel)
        }
        composable(
            route = Screen.ManagerMatchScreen.route + "?matchId={matchId}",
            arguments = listOf(
                navArgument("matchId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            ),
        ) { entry ->
            val viewModel: ManagerMatchViewModel = hiltViewModel()
            val matchId = entry.arguments?.getString("matchId")?.toLong()
            viewModel.loadMatch(matchId)
            ManagerMatchScreen(navController, viewModel)
        }
    }
}