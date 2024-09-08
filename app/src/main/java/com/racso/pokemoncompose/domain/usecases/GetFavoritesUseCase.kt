package com.racso.pokemoncompose.domain.usecases

import com.racso.pockemoncompose.data.local.entity.PokemonEntity
import com.racso.pockemoncompose.data.local.entity.toPokemonList
import com.racso.pokemoncompose.domain.models.Pokemon
import com.racso.pokemoncompose.data.repository.PokemonRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: PokemonRepositoryImpl){
    suspend operator fun invoke(): Flow<List<Pokemon>>{

        var favoritesList =  repository.getFavorites().map {
            it.toPokemonList()
        }
        return favoritesList
    }
}