package com.rmoralf.firestorecleanarchitecture.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.rmoralf.firestorecleanarchitecture.core.utils.Constants.FINISHED_FIELD
import com.rmoralf.firestorecleanarchitecture.core.utils.Constants.TITLE
import com.rmoralf.firestorecleanarchitecture.domain.model.Game
import com.rmoralf.firestorecleanarchitecture.domain.model.Response.*
import com.rmoralf.firestorecleanarchitecture.domain.repository.GamesRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val gamesRef: CollectionReference
) : GamesRepository {
    override fun getGames() = callbackFlow {
        val snapshotListener =
            gamesRef.orderBy(TITLE, Query.Direction.ASCENDING).addSnapshotListener { snapshot, e ->
                val response = if (snapshot != null) {
                    val games = snapshot.toObjects(Game::class.java)
                    Success(games)
                } else {
                    Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addGame(title: String, platform: String, finished: Boolean) = flow {
        try {
            emit(Loading)
            val gameId = gamesRef.document().id
            val newGame = Game(
                id = gameId,
                title = title,
                platform = platform,
                finished = finished
            )
            val addition = gamesRef.document(gameId).set(newGame).await()
            emit(Success(addition))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

    override suspend fun updateGame(gameId: String, finished: Boolean) = flow {
        try {
            emit(Loading)
            val update = gamesRef.document(gameId).update(FINISHED_FIELD, finished).await()
            emit(Success(update))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

    override suspend fun deleteGame(gameId: String) = flow {
        try {
            emit(Loading)
            val deletion = gamesRef.document(gameId).delete().await()
            emit(Success(deletion))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }
}