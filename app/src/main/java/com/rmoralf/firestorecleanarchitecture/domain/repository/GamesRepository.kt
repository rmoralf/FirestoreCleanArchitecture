package com.rmoralf.firestorecleanarchitecture.domain.repository

import com.rmoralf.firestorecleanarchitecture.domain.model.Game
import com.rmoralf.firestorecleanarchitecture.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    fun getGames(): Flow<Response<List<Game>>>

    suspend fun addGame(title: String, platform: String, finished: Boolean): Flow<Response<Void?>>

    suspend fun updateGame(gameId: String, finished: Boolean): Flow<Response<Void?>>

    suspend fun deleteGame(gameId: String): Flow<Response<Void?>>
}