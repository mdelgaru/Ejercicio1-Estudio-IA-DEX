package com.example.ejercicio1_estudio_ia_dex.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicio1_estudio_ia_dex.data.repository.PokemonRepositoryImpl
import com.example.ejercicio1_estudio_ia_dex.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase =
        GetPokemonListUseCase(PokemonRepositoryImpl())
) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 20
    }

    private val _state = MutableStateFlow(PokemonListState(isLoading = false))
    val state: StateFlow<PokemonListState> = _state

    init {
        loadNextPage() // Carga página 1
    }

    fun loadNextPage() {
        val currentState = _state.value
        if (currentState.isLoading || currentState.isEndReached) return

        viewModelScope.launch {
            _state.value = currentState.copy(isLoading = true, error = null)
            try {
                val offset = currentState.page * PAGE_SIZE
                val result = getPokemonListUseCase(limit = PAGE_SIZE, offset = offset)

                val newList = currentState.items + result

                _state.value = currentState.copy(
                    isLoading = false,
                    items = newList,
                    page = currentState.page + 1,
                    isEndReached = result.isEmpty()
                )
            } catch (e: Exception) {
                _state.value = currentState.copy(
                    isLoading = false,
                    error = e.message ?: "Error desconocido"
                )
            }
        }
    }

    fun retry() {
        // Reintentar la misma página
        _state.value = _state.value.copy(error = null)
        loadNextPage()
    }

    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }
}
