package com.example.ejercicio1_estudio_ia_dex.data.remote.mapper

import com.example.ejercicio1_estudio_ia_dex.data.remote.dto.PokemonDetailDto
import com.example.ejercicio1_estudio_ia_dex.data.remote.dto.PokemonItemDto
import com.example.ejercicio1_estudio_ia_dex.domain.model.Pokemon
import com.example.ejercicio1_estudio_ia_dex.domain.model.PokemonDetail

object PokemonMapper {

    fun toDomain(item: PokemonItemDto): Pokemon {

        val id = item.url.trimEnd('/').split("/").last().toIntOrNull() ?: 0
        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
        return Pokemon(
            id = id,
            name = item.name.replaceFirstChar { it.uppercase() },
            imageUrl = imageUrl
        )
    }

    fun toDomain(detail: PokemonDetailDto): PokemonDetail {
        val imageUrl = detail.sprites.front_default
            ?: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${detail.id}.png"

        return PokemonDetail(
            id = detail.id,
            name = detail.name.replaceFirstChar { it.uppercase() },
            imageUrl = imageUrl,
            types = detail.types.map { it.type.name.replaceFirstChar { c -> c.uppercase() } },
            height = detail.height,
            weight = detail.weight
        )
    }
}
