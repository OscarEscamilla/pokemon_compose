package com.racso.pokemoncompose.domain.models


data class Pokemon(
    val id: Int = -1,
    val name: String = "",
    val imageUrl: String = "",
    val weight: Int = 0,
    val height: Int = 0,
    val hp: Int = 0,
    val attack: Int = 0,
    val defense: Int = 0,
    val specialAtack: Int = 0,
    var favorite: Boolean = false,
    var color: String = ""
)


