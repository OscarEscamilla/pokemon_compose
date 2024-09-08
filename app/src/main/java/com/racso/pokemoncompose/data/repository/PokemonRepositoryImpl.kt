package com.racso.pokemoncompose.data.repository

import com.racso.pockemoncompose.data.local.entity.PokemonEntity
import com.racso.pockemoncompose.data.local.entity.toPokemonList
import com.racso.pockemoncompose.data.remote.PokeApiService
import com.racso.pokemoncompose.data.local.dao.PokemonDao
import com.racso.pokemoncompose.domain.models.Pokemon
import com.racso.pokemoncompose.data.remote.responses.*
import com.racso.pokemoncompose.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val api: PokeApiService, private  val pokemonDao: PokemonDao):
    PokemonRepository {


    override suspend fun getPokemonsFromApi(offset: Int): List<Pokemon> {
        return api.getPokemons(20, offset).toPokemonList()
    }

    override suspend fun getPokemon(id: Int): Pokemon {
        return api.getPokemon(id).toPokemon()
    }

    override suspend fun clearPokemonTable() {
        pokemonDao.deletePokemons()
    }

    override suspend fun getAllPokemonsFromDatabase(): List<Pokemon> {
        return pokemonDao.getAllPokemons().toPokemonList()
    }

    override suspend fun savePokemons(pokemonList: List<PokemonEntity>){
        pokemonDao.insertAllPokemons(pokemonList)
    }

    override suspend fun getFavorites() = pokemonDao.getFavoritesPokemon()


    override suspend fun saveFavorite(pokemonEntity: PokemonEntity){
        pokemonDao.updateToFavoritePokemon(pokemonEntity)
    }

}