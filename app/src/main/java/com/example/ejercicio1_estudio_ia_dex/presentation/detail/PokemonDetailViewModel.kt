package com.example.ejercicio1_estudio_ia_dex.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicio1_estudio_ia_dex.data.repository.PokemonRepositoryImpl
import com.example.ejercicio1_estudio_ia_dex.domain.usecase.GetPokemonDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase =
        GetPokemonDetailUseCase(PokemonRepositoryImpl()),
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        const val ARG_POKEMON_ID = "pokemonId"
    }

    private val _state = MutableStateFlow(PokemonDetailState(isLoading = true))
    val state: StateFlow<PokemonDetailState> = _state

    private var currentPokemonId: Int = -1

    init {
        val pokemonId: Int = savedStateHandle[ARG_POKEMON_ID] ?: -1
        currentPokemonId = pokemonId
        if (pokemonId != -1) {
            loadDetail(pokemonId)
        } else {
            _state.value = PokemonDetailState(
                isLoading = false,
                error = "Id de Pokémon no válido"
            )
        }
    }

    private fun loadDetail(id: Int) {
        viewModelScope.launch {
            _state.value = PokemonDetailState(isLoading = true)
            try {
                val detail = getPokemonDetailUseCase(id)
                _state.value = PokemonDetailState(
                    isLoading = false,
                    data = detail
                )
            } catch (e: Exception) {
                _state.value = PokemonDetailState(
                    isLoading = false,
                    error = e.message ?: "Error al cargar detalle"
                )
            }
        }
    }

    fun retry() {
        if (currentPokemonId != -1) {
            loadDetail(currentPokemonId)
        }
    }

    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }
}
