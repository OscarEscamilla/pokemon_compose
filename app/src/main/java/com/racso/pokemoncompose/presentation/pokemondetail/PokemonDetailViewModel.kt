package com.racso.pokemoncompose.presentation.pokemondetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.racso.pokemoncompose.domain.models.Pokemon
import com.racso.pokemoncompose.domain.usecases.GetPokemonByIdUseCase
import com.racso.pokemoncompose.domain.usecases.SaveFavoritePokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase,
    private val saveFavoritePokemonUseCase: SaveFavoritePokemonUseCase
) : ViewModel() {

    private val _pokemon = MutableStateFlow<Pokemon?>(null)
    val pokemon: StateFlow<Pokemon?> = _pokemon

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _favoriteStatus = MutableStateFlow(false)
    val favoriteStatus: StateFlow<Boolean> get() = _favoriteStatus

    fun getPokemon(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val pokemonDetails = getPokemonByIdUseCase(id)
                _favoriteStatus.value = pokemonDetails.favorite
                _pokemon.value = pokemonDetails
            } catch (e: Exception) {
                _pokemon.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun saveFavoritePokemon(pokemon: Pokemon) {
        viewModelScope.launch {
            try {
                saveFavoritePokemonUseCase.invoke(pokemon)
                _favoriteStatus.value = !_favoriteStatus.value
            } catch (e: Exception) {
                _favoriteStatus.value = false
            }
        }
    }
}