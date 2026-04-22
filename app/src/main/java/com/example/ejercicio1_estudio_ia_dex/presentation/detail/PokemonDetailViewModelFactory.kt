package com.example.ejercicio1_estudio_ia_dex.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import com.example.ejercicio1_estudio_ia_dex.data.repository.PokemonRepositoryImpl
import com.example.ejercicio1_estudio_ia_dex.domain.usecase.GetPokemonDetailUseCase

class PokemonDetailViewModelFactory(
    private val savedStateHandle: SavedStateHandle
) : Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonDetailViewModel::class.java)) {
            val getPokemonDetailUseCase = GetPokemonDetailUseCase(PokemonRepositoryImpl())
            return PokemonDetailViewModel(
                getPokemonDetailUseCase = getPokemonDetailUseCase,
                savedStateHandle = savedStateHandle
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
