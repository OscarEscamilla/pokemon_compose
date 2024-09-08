package com.racso.pokemoncompose.data.remote.responses

import com.racso.pokemoncompose.utils.Constants
import com.racso.pokemoncompose.domain.models.Pokemon

data class PokemonEntry(
    val name: String = "",
    val url: String = "")

fun PokemonEntry.getImageUrl(): String {
    val imgUrlArray = this.url.split("/")
    val imgName = imgUrlArray[imgUrlArray.size - 2] + ".png"
    return Constants.IMAGES_URL + imgName
}

fun PokemonEntry.getId(): Int {
    val imgUrlArray = this.url.split("/")
    val id = imgUrlArray[imgUrlArray.size - 2].toInt()
    return id
}

fun PokemonEntry.toPokemon() = Pokemon(
    id = this.getId(),
    name = name,
    imageUrl = this.getImageUrl()
)

