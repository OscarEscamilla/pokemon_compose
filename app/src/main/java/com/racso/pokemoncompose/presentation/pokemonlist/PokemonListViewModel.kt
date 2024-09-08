package com.racso.pokemoncompose.presentation.pokemonlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.racso.pokemoncompose.domain.models.Pokemon
import com.racso.pokemoncompose.domain.usecases.GetFavoritesUseCase
import com.racso.pokemoncompose.domain.usecases.GetPokemonsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonsListUseCase: GetPokemonsListUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _filteredPokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val filteredPokemonList: StateFlow<List<Pokemon>> = _filteredPokemonList

    private var currentOffset = 0
    private var isLastPage = false

    init {
        getPokemonsList()

        viewModelScope.launch {
            getFavoritesUseCase.invoke().collect { favorites ->
                // Update _filteredList with favorite pokemon
                _filteredPokemonList.value = _filteredPokemonList.value.map { pokemon ->
                    if (favorites.any { it.id == pokemon.id }) {
                        pokemon.copy(favorite = true)
                    } else {
                        pokemon.copy(favorite = false)
                    }
                }
            }
        }
    }

    fun getPokemonsList() {
        if (_isLoading.value || isLastPage) return

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val pokemons = getPokemonsListUseCase.invoke(currentOffset)
                if (pokemons.isEmpty()) {
                    isLastPage = true
                } else {
                    _pokemonList.value += pokemons
                    currentOffset += pokemons.size
                    filterPokemons(_searchQuery.value)
                }
            } catch (e: Exception) {
                _isLoading.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        filterPokemons(query)
    }

    private fun filterPokemons(query: String) {
        _filteredPokemonList.value = _pokemonList.value.filter { pokemon ->
            pokemon.name.contains(query, ignoreCase = true)
        }
    }

    fun filterPokemonsFavorites() {
        _filteredPokemonList.value = _pokemonList.value.filter { pokemon ->
            pokemon.favorite == true
        }
    }
}
