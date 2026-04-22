package com.example.ejercicio1_estudio_ia_dex.domain.repository

import com.example.ejercicio1_estudio_ia_dex.domain.model.Pokemon
import com.example.ejercicio1_estudio_ia_dex.domain.model.PokemonDetail

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon>
    suspend fun getPokemonDetail(id: Int): PokemonDetail
}