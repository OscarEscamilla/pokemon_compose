package com.racso.pokemoncompose.domain.usecases

import com.racso.pockemoncompose.data.local.entity.toPokemonList
import com.racso.pokemoncompose.domain.models.Pokemon
import com.racso.pokemoncompose.data.repository.PokemonRepositoryImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPokemonByIdUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepositoryImpl
) {
    suspend operator fun invoke(id: Int): Pokemon {
        val pokemon = pokemonRepository.getPokemon(id)
        val favorites = pokemonRepository.getFavorites().first().toPokemonList()

        if (favorites.any { it.id == pokemon.id }) {
            pokemon.favorite = true
        }

        return pokemon
    }
}