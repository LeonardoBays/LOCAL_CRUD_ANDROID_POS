package com.br.leonardo.bays.crud.ui.match.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.leonardo.bays.crud.domain.model.Match
import com.br.leonardo.bays.crud.viewmodel.match.MatchViewModel

@Composable
fun MatchScore(viewModel: MatchViewModel, match: Match) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MatchTeamScore(
                teamName = match.homeTeam,
                score = match.homeScore,
                onScoreIncrease = { viewModel.increaseHome() },
                onScoreDecrease = { viewModel.decreaseHome() }
            )

            Spacer(modifier = Modifier.width(32.dp))

            Text(
                text = "-",
                fontSize = 80.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(bottom = 120.dp)
            )

            Spacer(modifier = Modifier.width(32.dp))

            MatchTeamScore(
                teamName = match.awayTeam,
                score = match.awayScore,
                onScoreIncrease = { viewModel.increaseAway() },
                onScoreDecrease = { viewModel.decreaseAway() }
            )
        }
    }
}

