package com.racso.pokemoncompose.domain.repository

import com.racso.pockemoncompose.data.local.entity.PokemonEntity
import com.racso.pokemoncompose.domain.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonsFromApi(offset: Int): List<Pokemon>

    suspend fun getPokemon(id: Int): Pokemon

    suspend fun clearPokemonTable()

    suspend fun getAllPokemonsFromDatabase(): List<Pokemon>

    suspend fun savePokemons(pokemonList: List<PokemonEntity>)

    suspend fun getFavorites(): Flow<List<PokemonEntity>>

    suspend fun saveFavorite(pokemonEntity: PokemonEntity)
}