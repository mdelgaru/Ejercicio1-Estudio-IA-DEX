package com.example.ejercicio1_estudio_ia_dex.data.remote.dto

data class PokemonListResponseDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonItemDto>
)

data class PokemonItemDto(
    val name: String,
    val url: String
)

data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonTypeSlotDto>,
    val sprites: PokemonSpritesDto
)

data class PokemonTypeSlotDto(
    val slot: Int,
    val type: PokemonTypeDto
)

data class PokemonTypeDto(
    val name: String
)

data class PokemonSpritesDto(
    val front_default: String?
)
