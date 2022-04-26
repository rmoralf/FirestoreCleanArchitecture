package com.rmoralf.firestorecleanarchitecture.presentation.games

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmoralf.firestorecleanarchitecture.domain.model.Game
import com.rmoralf.firestorecleanarchitecture.domain.model.Response
import com.rmoralf.firestorecleanarchitecture.domain.model.Response.Loading
import com.rmoralf.firestorecleanarchitecture.domain.model.Response.Success
import com.rmoralf.firestorecleanarchitecture.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    var openDialogState = mutableStateOf(false)

    private val _gamesState = mutableStateOf<Response<List<Game>>>(Loading)
    val gamesState: State<Response<List<Game>>> = _gamesState

    private val _isGameAddedState = mutableStateOf<Response<Void?>>(Success(null))
    val isGameAddedState: State<Response<Void?>> = _isGameAddedState

    private val _isGameUpdatedState = mutableStateOf<Response<Void?>>(Success(null))
    val isGameUpdatedState: State<Response<Void?>> = _isGameUpdatedState

    private val _isGameDeletedState = mutableStateOf<Response<Void?>>(Success(null))
    val isGameDeletedState: State<Response<Void?>> = _isGameDeletedState

    init {
        getGames()
    }

    private fun getGames() {
        viewModelScope.launch {
            useCases.getGames().collect { response ->
                _gamesState.value = response
            }
        }
    }

    fun addGame(title: String, platform: String, finished: Boolean) {
        viewModelScope.launch {
            useCases.addGame(title, platform, finished).collect { response ->
                _isGameAddedState.value = response
            }
        }
    }

    fun updateGame(gameId: String, newFinished: Boolean) {
        viewModelScope.launch {
            useCases.updateGame(
                gameId = gameId,
                finished = newFinished,
            ).collect { response ->
                _isGameAddedState.value = response
            }
        }
    }

    fun deleteGame(gameId: String) {
        viewModelScope.launch {
            useCases.deleteGame(gameId).collect { response ->
                _isGameDeletedState.value = response
            }
        }
    }
}