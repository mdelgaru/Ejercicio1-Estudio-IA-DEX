package com.example.ejercicio1_estudio_ia_dex.data.remote.api

import com.example.ejercicio1_estudio_ia_dex.data.remote.dto.PokemonDetailDto
import com.example.ejercicio1_estudio_ia_dex.data.remote.dto.PokemonListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponseDto

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: Int
    ): PokemonDetailDto
}
