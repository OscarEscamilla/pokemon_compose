package com.racso.pokemoncompose.data.local.dao

import androidx.room.*
import com.racso.pockemoncompose.data.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    suspend fun getAllPokemons(): List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE favorite = 1")
    fun getFavoritesPokemon(): Flow<List<PokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllPokemons(pokemonList : List<PokemonEntity>)

    @Query("DELETE FROM pokemon WHERE favorite = 1")
    suspend fun deletePokemons()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateToFavoritePokemon(pokemonEntity: PokemonEntity)

}