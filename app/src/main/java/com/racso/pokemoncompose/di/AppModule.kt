package com.racso.pokemoncompose.di

import com.racso.pockemoncompose.data.remote.PokeApiService
import com.racso.pokemoncompose.data.local.dao.PokemonDao
import com.racso.pokemoncompose.domain.repository.PokemonRepository
import com.racso.pokemoncompose.data.repository.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providePokemonRepository(
        apiService: PokeApiService,
        pokemonDao: PokemonDao
    ): PokemonRepository {
        return PokemonRepositoryImpl(apiService, pokemonDao)
    }
}