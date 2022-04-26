package com.rmoralf.firestorecleanarchitecture.domain.usecases

import com.rmoralf.firestorecleanarchitecture.domain.repository.GamesRepository

class GetGames(
    private val repository: GamesRepository
) {
    operator fun invoke() = repository.getGames()
}