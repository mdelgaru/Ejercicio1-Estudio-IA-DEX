package com.example.ejercicio1_estudio_ia_dex.presentation.list

import com.example.ejercicio1_estudio_ia_dex.domain.model.Pokemon

data class PokemonListState(
    val isLoading: Boolean = false,
    val items: List<Pokemon> = emptyList(),
    val error: String? = null,
    val isEndReached: Boolean = false,
    val page: Int = 0
)
