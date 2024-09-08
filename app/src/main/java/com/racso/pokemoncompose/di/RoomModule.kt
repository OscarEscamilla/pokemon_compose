package com.racso.pokemoncompose.di

import android.content.Context
import androidx.room.Room
import com.racso.pokemoncompose.utils.Constants
import com.racso.pokemoncompose.data.local.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PokemonDatabase::class.java, Constants.POKEMON_DATABASE).build()

    @Singleton
    @Provides
    fun providePokemonDao(db: PokemonDatabase) = db.getPokemonDao()


}