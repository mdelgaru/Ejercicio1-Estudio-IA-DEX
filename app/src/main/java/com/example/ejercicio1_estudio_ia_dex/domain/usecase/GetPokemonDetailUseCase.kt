package com.example.ejercicio1_estudio_ia_dex.domain.usecase

import com.example.ejercicio1_estudio_ia_dex.domain.model.PokemonDetail
import com.example.ejercicio1_estudio_ia_dex.domain.repository.PokemonRepository

class GetPokemonDetailUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): PokemonDetail =
        repository.getPokemonDetail(id)
}
