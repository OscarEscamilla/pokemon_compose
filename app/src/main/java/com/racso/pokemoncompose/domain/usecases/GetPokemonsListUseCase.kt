package com.racso.pokemoncompose.domain.usecases

import com.racso.pockemoncompose.data.local.entity.toPokemonList
import com.racso.pokemoncompose.data.repository.PokemonRepositoryImpl
import com.racso.pokemoncompose.domain.repository.PokemonRepository
import com.racso.pokemoncompose.domain.models.Pokemon
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetPokemonsListUseCase @Inject constructor(
    private val repository: PokemonRepositoryImpl
    ) {
    suspend operator fun  invoke(offset: Int): List<Pokemon> {
        val pokemonList = repository.getPokemonsFromApi(offset)
        val favorites = repository.getFavorites().first().toPokemonList()

        pokemonList.forEach { pokemon ->
            if (favorites.any { it.id == pokemon.id }) {
                pokemon.favorite = true
            }
        }

        return pokemonList
    }
}