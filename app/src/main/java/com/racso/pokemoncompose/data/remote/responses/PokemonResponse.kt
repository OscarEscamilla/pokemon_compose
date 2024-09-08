package com.racso.pokemoncompose.data.remote.responses


import com.google.gson.annotations.SerializedName
import com.racso.pokemoncompose.domain.models.Pokemon
import com.racso.pokemoncompose.utils.Constants

data class PokemonResponse(
    val height: Int,
    val id: Int,
    val name: String,
    val stats: List<Stat>,
    val weight: Int
)

fun PokemonResponse.toPokemon() = Pokemon(
    id = id,
    name = name,
    imageUrl = "${Constants.IMAGES_URL}$id.png",
    weight = weight,
    height = height,
    hp = stats[0].baseStat,
    attack = stats[1].baseStat,
    defense = stats[2].baseStat,
    specialAtack = stats[3].baseStat
)



data class Stat(
    @SerializedName("base_stat")
    val baseStat: Int,
    val effort: Int,
)

