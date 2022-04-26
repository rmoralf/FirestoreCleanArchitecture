package com.rmoralf.firestorecleanarchitecture.presentation.games.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmoralf.firestorecleanarchitecture.R
import com.rmoralf.firestorecleanarchitecture.presentation.games.GamesViewModel

@Composable
fun GamesTopBar(
    viewModel: GamesViewModel = hiltViewModel()
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.appbar_title))
        },
        actions = {
            IconButton(onClick = {
                viewModel.openDialogState.value = true
            }) {
                Icon(Icons.Default.Add, stringResource(id = R.string.appbar_add))
            }
        }
    )
}