package com.racso.pokemoncompose.domain.usecases

import com.racso.pockemoncompose.data.local.entity.toEntity
import com.racso.pokemoncompose.domain.models.Pokemon
import com.racso.pokemoncompose.data.repository.PokemonRepositoryImpl
import javax.inject.Inject

class SaveFavoritePokemonUseCase @Inject constructor(
    private val repository: PokemonRepositoryImpl) {
    suspend operator fun invoke(pokemon: Pokemon){
        pokemon.favorite = !pokemon.favorite
        repository.saveFavorite(pokemon.toEntity())
    }
}