package com.br.leonardo.bays.crud.ui.home.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.br.leonardo.bays.crud.R
import com.br.leonardo.bays.crud.ui.Screen
import com.br.leonardo.bays.crud.viewmodel.home.HomeViewModel

@Composable
fun HomeEmptyList(navController: NavController, viewModel: HomeViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_list),
            contentDescription = "Minha imagem",
        )
        Text(
            text = "Sua lista de jogos está vazia.",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier.padding(top = 26.dp),
            text = "Para começar, é só registrar um jogo e convidar seus amigos.",
            textAlign = TextAlign.Center,
        )
        ElevatedButton(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(.75f),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
            ),
            onClick = { navController.navigate(Screen.ManagerMatchScreen.route) }
        ) {
            Text("Criar jogo")
        }
    }

}