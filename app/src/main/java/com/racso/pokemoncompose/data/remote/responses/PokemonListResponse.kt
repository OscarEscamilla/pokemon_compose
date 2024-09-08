package com.racso.pokemoncompose.data.remote.responses

import com.racso.pokemoncompose.domain.models.Pokemon


data class PokemonListResponse(val results: ArrayList<PokemonEntry> = arrayListOf())


fun PokemonListResponse.toPokemonList(): List<Pokemon> {
    return this.results.map { it.toPokemon() }
}
