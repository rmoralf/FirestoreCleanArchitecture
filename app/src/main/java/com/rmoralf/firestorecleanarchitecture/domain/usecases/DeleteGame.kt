package com.rmoralf.firestorecleanarchitecture.domain.usecases

import com.rmoralf.firestorecleanarchitecture.domain.repository.GamesRepository

class DeleteGame(
    private val repository: GamesRepository
) {
    suspend operator fun invoke(gameId: String) = repository.deleteGame(gameId)
}