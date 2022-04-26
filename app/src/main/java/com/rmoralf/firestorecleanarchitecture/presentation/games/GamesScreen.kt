package com.rmoralf.firestorecleanarchitecture.presentation.games

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmoralf.firestorecleanarchitecture.core.utils.Utils.Companion.printError
import com.rmoralf.firestorecleanarchitecture.domain.model.Response.*
import com.rmoralf.firestorecleanarchitecture.presentation.components.ProgressBar
import com.rmoralf.firestorecleanarchitecture.presentation.games.components.AddGameDialog
import com.rmoralf.firestorecleanarchitecture.presentation.games.components.GamesListHeader
import com.rmoralf.firestorecleanarchitecture.presentation.games.components.GamesListItem
import com.rmoralf.firestorecleanarchitecture.presentation.games.components.GamesTopBar
import com.rmoralf.firestorecleanarchitecture.presentation.ui.theme.DividerBg

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GamesScreen(
    viewModel: GamesViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            GamesTopBar()
        }
    ) {
        if (viewModel.openDialogState.value) {
            AddGameDialog()
        }
        when (val gamesResponse = viewModel.gamesState.value) {
            is Loading -> ProgressBar()
            is Success -> Box(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn {
                    stickyHeader {
                        GamesListHeader()
                    }
                    val gameList = gamesResponse.data
                    itemsIndexed(gameList) { index, item ->
                        GamesListItem(game = item)
                        if (index < gameList.lastIndex)
                            Divider(color = DividerBg)
                    }
                }
            }
            is Error -> printError(gamesResponse.message)
        }
        when (val deletionResponse = viewModel.isGameAddedState.value) {
            is Loading -> ProgressBar()
            is Success -> Unit
            is Error -> printError(deletionResponse.message)
        }
        when (val deletionResponse = viewModel.isGameUpdatedState.value) {
            is Loading -> ProgressBar()
            is Success -> Unit
            is Error -> printError(deletionResponse.message)
        }
        when (val deletionResponse = viewModel.isGameDeletedState.value) {
            is Loading -> ProgressBar()
            is Success -> Unit
            is Error -> printError(deletionResponse.message)
        }

    }

}