package com.racso.pokemoncompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.racso.pockemoncompose.data.local.entity.PokemonEntity
import com.racso.pokemoncompose.data.local.dao.PokemonDao


@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao
}