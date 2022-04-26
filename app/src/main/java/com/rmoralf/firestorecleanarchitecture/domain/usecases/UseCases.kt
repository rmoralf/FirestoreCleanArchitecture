package com.rmoralf.firestorecleanarchitecture.domain.usecases

data class UseCases(
    //Firestore
    val getGames: GetGames,
    val addGame: AddGame,
    val updateGame: UpdateGame,
    val deleteGame: DeleteGame
)
