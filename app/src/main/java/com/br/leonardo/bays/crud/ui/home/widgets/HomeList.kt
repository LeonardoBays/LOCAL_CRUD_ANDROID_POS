package com.br.leonardo.bays.crud.ui.home.widgets

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.br.leonardo.bays.crud.domain.model.Match

@Composable
fun HomeList(navController: NavController, matches: List<Match>) {
    LazyColumn {
        items(matches) { match ->
            HomeCard(navController = navController, match = match)
        }
    }
}