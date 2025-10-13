package com.br.leonardo.bays.crud.ui.home.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeFAB() {
    FloatingActionButton(
        onClick = ::onPressed,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Adicionar novo jogo"
        )
    }
}

private fun onPressed() {
    println("Pressed")
}

@Preview
@Composable
private fun HomeFABPreview() {
    HomeFAB()
}