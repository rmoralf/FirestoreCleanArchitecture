package com.rmoralf.firestorecleanarchitecture.domain.usecases

import com.rmoralf.firestorecleanarchitecture.domain.repository.GamesRepository

class AddGame(
    private val repository: GamesRepository
) {
    suspend operator fun invoke(title: String, platform: String, finished: Boolean) =
        repository.addGame(title, platform, finished)
}