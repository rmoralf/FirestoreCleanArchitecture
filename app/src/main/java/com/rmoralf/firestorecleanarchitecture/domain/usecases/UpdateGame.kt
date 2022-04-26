package com.rmoralf.firestorecleanarchitecture.domain.usecases

import com.rmoralf.firestorecleanarchitecture.domain.repository.GamesRepository

class UpdateGame(
    private val repository: GamesRepository
) {
    suspend operator fun invoke(gameId: String, finished: Boolean) =
        repository.updateGame(gameId, finished)
}