package com.racso.pockemoncompose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.racso.pokemoncompose.domain.models.Pokemon


@Entity(tableName = "pokemon")
data class PokemonEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = -1,
    @ColumnInfo("name") val name: String = "",
    @ColumnInfo("imageUrl") val imageUrl: String = "",
    @ColumnInfo("weight") val weight: Int = 0,
    @ColumnInfo("height") val height: Int = 0,
    @ColumnInfo("hp") val hp: Int = 0,
    @ColumnInfo("attack") val attack: Int = 0,
    @ColumnInfo("defense") val defense: Int = 0,
    @ColumnInfo("special-attack") val specialAtack: Int = 0,
    @ColumnInfo("favorite") val favorite: Boolean = true,
    )


fun List<PokemonEntity>.toPokemonList(): List<Pokemon>{
    return this.map { it.toPokemon() }
}

fun PokemonEntity.toPokemon() = Pokemon(
    id = id,
    name = name,
    imageUrl = imageUrl,
    weight = weight,
    height = height,
    hp = hp,
    attack = attack,
    defense = defense,
    specialAtack = specialAtack,
    favorite = favorite
)


fun Pokemon.toEntity(): PokemonEntity {
    val pokemonEntity = PokemonEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        weight = weight,
        height = height,
        hp = hp,
        attack = attack,
        defense = defense,
        specialAtack = specialAtack,
        favorite = favorite
    )
    return pokemonEntity
}





