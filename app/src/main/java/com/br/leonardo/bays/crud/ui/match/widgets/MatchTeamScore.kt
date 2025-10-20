package com.br.leonardo.bays.crud.ui.match.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RowScope.MatchTeamScore(
    teamName: String,
    score: Long,
    onScoreIncrease: () -> Unit,
    onScoreDecrease: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.weight(1f)
    ) {
        Text(
            text = teamName.uppercase(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = score.toString(),
            fontSize = 80.sp,
            fontWeight = FontWeight.ExtraBold,
        )

        Button(
            onClick = onScoreIncrease,
            shape = CircleShape,
            modifier = Modifier.size(60.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Aumentar Placar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = onScoreDecrease,
            shape = CircleShape,
            modifier = Modifier.size(60.dp)
        ) {
            Icon(
                Icons.Filled.Remove,
                contentDescription = "Diminuir Placar",
            )
        }
    }
}