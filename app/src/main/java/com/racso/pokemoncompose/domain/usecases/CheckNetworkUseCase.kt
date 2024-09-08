package com.racso.pokemoncompose.domain.usecases

import com.racso.pokemoncompose.utils.InternetCheck

class CheckNetworkUseCase{
    suspend operator fun invoke(): Boolean {
        return InternetCheck.isNetworkAvailable()
    }
}