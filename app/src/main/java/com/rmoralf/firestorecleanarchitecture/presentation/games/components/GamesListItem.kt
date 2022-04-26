package com.rmoralf.firestorecleanarchitecture.presentation.games.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmoralf.firestorecleanarchitecture.R
import com.rmoralf.firestorecleanarchitecture.domain.model.Game
import com.rmoralf.firestorecleanarchitecture.domain.model.samples.SampleGameProvider
import com.rmoralf.firestorecleanarchitecture.presentation.games.GamesViewModel

@Preview(showBackground = true)
@Composable
fun GamesListItem(
    @PreviewParameter(SampleGameProvider::class, 4)
    game: Game,
    viewModel: GamesViewModel = hiltViewModel()
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = game.title ?: "",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(2f)
                .align(CenterVertically)
        )

        Text(
            text = game.platform ?: "",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(2f)
                .align(CenterVertically)
        )

        Checkbox(
            checked = game.finished ?: false,
            onCheckedChange = { newFinished ->
                game.id?.let {
                    viewModel.updateGame(it, newFinished)
                }
            },
            modifier = Modifier
                .weight(2f)
                .align(CenterVertically)
        )
        IconButton(
            onClick = {
                game.id?.let { gameId ->
                    viewModel.deleteGame(gameId)
                }
            },
            modifier = Modifier
                .weight(1f)
                .align(CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.item_delete),
                tint = MaterialTheme.colors.onSurface
            )
        }


    }

}