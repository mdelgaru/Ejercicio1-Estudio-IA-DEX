package com.example.ejercicio1_estudio_ia_dex.domain.usecase

import com.example.ejercicio1_estudio_ia_dex.domain.model.Pokemon
import com.example.ejercicio1_estudio_ia_dex.domain.repository.PokemonRepository

class GetPokemonListUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int, offset: Int): List<Pokemon> =
        repository.getPokemonList(limit, offset)
}
