package com.example.ejercicio1_estudio_ia_dex.presentation.detail

import com.example.ejercicio1_estudio_ia_dex.domain.model.PokemonDetail

data class PokemonDetailState(
    val isLoading: Boolean = false,
    val data: PokemonDetail? = null,
    val error: String? = null
)
