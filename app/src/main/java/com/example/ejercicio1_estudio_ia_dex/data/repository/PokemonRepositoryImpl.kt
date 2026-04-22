package com.example.ejercicio1_estudio_ia_dex.data.repository

import com.example.ejercicio1_estudio_ia_dex.data.di.NetworkModule
import com.example.ejercicio1_estudio_ia_dex.data.remote.api.PokemonApi
import com.example.ejercicio1_estudio_ia_dex.data.remote.mapper.PokemonMapper
import com.example.ejercicio1_estudio_ia_dex.domain.model.Pokemon
import com.example.ejercicio1_estudio_ia_dex.domain.model.PokemonDetail
import com.example.ejercicio1_estudio_ia_dex.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val api: PokemonApi = NetworkModule.pokemonApi
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon> {
        val response = api.getPokemonList(limit = limit, offset = offset)
        return response.results.map { PokemonMapper.toDomain(it) }
    }

    override suspend fun getPokemonDetail(id: Int): PokemonDetail {
        val dto = api.getPokemonDetail(id)
        return PokemonMapper.toDomain(dto)
    }
}