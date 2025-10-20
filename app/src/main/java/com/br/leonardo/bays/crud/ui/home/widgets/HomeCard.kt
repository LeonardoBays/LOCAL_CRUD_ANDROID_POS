package com.br.leonardo.bays.crud.ui.home.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import com.br.leonardo.bays.crud.domain.model.Match
import com.br.leonardo.bays.crud.ui.Screen

@Composable
fun HomeCard(navController: NavController, match: Match) {
    Card(
        onClick = {
            if (match.isInProgress()) {
                navController.navigate(Screen.MatchScreen.route + "/${match.id}")
                return@Card
            }

            if (!match.isFinished()) {
                navController.navigate(Screen.ManagerMatchScreen.route + "?matchId=${match.id}")
                return@Card
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(
                        text = match.homeTeam,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight(),
                        textAlign = TextAlign.Start,
                        maxLines = Int.MAX_VALUE,
                        overflow = TextOverflow.Visible
                    )
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = match.homeScore.toString())
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = " - ")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = match.awayScore.toString())
                    }

                    Text(
                        text = match.awayTeam,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight(),
                        textAlign = TextAlign.End,
                        maxLines = Int.MAX_VALUE,
                        overflow = TextOverflow.Visible
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = match.statusPartida())
            }
        }

    }
}