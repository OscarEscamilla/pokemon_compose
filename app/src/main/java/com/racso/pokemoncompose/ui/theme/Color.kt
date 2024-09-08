package com.racso.pokemoncompose.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


val Red80 = Color(0xFFDB9696)
val RedGrey80 = Color(0xFFB89292)
val RedPink80 = Color(0xFFCEA4B0)

val Red40 = Color(0xFFC44949)
val RedGray40 = Color(0xFF5C4C4C)
val RedPink40 = Color(0xFF7D5260)

// Stats Colors
val RedStat = Color(0xFFCE4A4A)
val GreenStat = Color(0xFF62A865)
val BlueStat = Color(0xFF4090CE)
val YellowStat = Color(0xFFFFB74D)

// gradient

val baseGrdient = Color(0xFFA33B3B)
val CardGradient = Brush.linearGradient(
    colors = listOf(baseGrdient, RedPink80),
    //colors = listOf(RedPink80, RedPink40),
    start = Offset(0f, 0f),
    end = Offset(1000f, 1000f)
)


val GrayLigth = Color(0xFFCECCCC)



