package com.rmoralf.firestorecleanarchitecture.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.rmoralf.firestorecleanarchitecture.core.utils.Constants.GAMES
import com.rmoralf.firestorecleanarchitecture.data.repository.GamesRepositoryImpl
import com.rmoralf.firestorecleanarchitecture.domain.repository.GamesRepository
import com.rmoralf.firestorecleanarchitecture.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
object AppModule {

    //Repositories
    @Provides
    fun provideGamesRef(db: FirebaseFirestore) = db.collection(GAMES)

    @Provides
    fun provideGamesRepository(
        gamesRef: CollectionReference,
    ): GamesRepository = GamesRepositoryImpl(gamesRef)

    @Provides
    fun provideUseCases(
        gamesRepository: GamesRepository
    ) = UseCases(
        getGames = GetGames(gamesRepository),
        addGame = AddGame(gamesRepository),
        updateGame = UpdateGame(gamesRepository),
        deleteGame = DeleteGame(gamesRepository)
    )

    //Firebase
    @Provides
    fun provideFirestoreInstance() = FirebaseFirestore.getInstance()
}