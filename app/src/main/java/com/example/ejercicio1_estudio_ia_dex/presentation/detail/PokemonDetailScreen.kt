package com.example.ejercicio1_estudio_ia_dex.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.ejercicio1_estudio_ia_dex.domain.model.PokemonDetail
import com.example.ejercicio1_estudio_ia_dex.presentation.common.ErrorModal

// Enum para los colores de los tipos de Pokémon
enum class PokemonType(val backgroundColor: Color, val textColor: Color) {
    NORMAL(Color(0xFFA8A878), Color.White),
    FIRE(Color(0xFFF08030), Color.White),
    WATER(Color(0xFF6890F0), Color.White),
    ELECTRIC(Color(0xFFF8D030), Color.Black),
    GRASS(Color(0xFF78C850), Color.White),
    ICE(Color(0xFF98D8D8), Color.Black),
    FIGHTING(Color(0xFFC03028), Color.White),
    POISON(Color(0xFFA040A0), Color.White),
    GROUND(Color(0xFFE0C068), Color.Black),
    FLYING(Color(0xFFA890F0), Color.White),
    PSYCHIC(Color(0xFFF85888), Color.White),
    BUG(Color(0xFFA8B820), Color.White),
    ROCK(Color(0xFFB8A038), Color.White),
    GHOST(Color(0xFF705898), Color.White),
    DRAGON(Color(0xFF7038F8), Color.White),
    DARK(Color(0xFF705848), Color.White),
    STEEL(Color(0xFFB8B8D0), Color.Black),
    FAIRY(Color(0xFFEE99AC), Color.White);

    companion object {
        fun fromString(type: String): PokemonType {
            return try {
                valueOf(type.uppercase())
            } catch (e: IllegalArgumentException) {
                NORMAL
            }
        }
    }
}

@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            state.data != null -> {
                state.data?.let { detail ->
                    DetailContent(detail = detail, onBack = onBack)
                }
            }
        }

        // Modal de error superpuesto
        ErrorModal(
            errorMessage = state.error,
            onDismiss = { viewModel.clearError() },
            onRetry = { viewModel.retry() }
        )
    }
}

@Composable
private fun DetailContent(
    detail: PokemonDetail,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header con botón atrás y nombre/ID
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFDC0A2D))
                .padding(vertical = 12.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "${detail.name.replaceFirstChar { it.uppercase() }} #${detail.id.toString().padStart(4, '0')}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // Contenido scrolleable tipo carta de Pokémon
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Carta de Pokémon
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Imagen del Pokémon
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF0F0F0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(detail.imageUrl),
                            contentDescription = detail.name,
                            modifier = Modifier.size(180.dp),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Tipos con badges de colores
                    Text(
                        text = "Tipos",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        detail.types.forEach { type ->
                            TypeBadge(type = type)
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color(0xFFE0E0E0)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Información física
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        InfoCard(
                            label = "Altura",
                            value = "${detail.height / 10.0} m"
                        )

                        InfoCard(
                            label = "Peso",
                            value = "${detail.weight / 10.0} kg"
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TypeBadge(type: String) {
    val pokemonType = PokemonType.fromString(type)
    
    Surface(
        modifier = Modifier
            .padding(4.dp),
        shape = RoundedCornerShape(20.dp),
        color = pokemonType.backgroundColor
    ) {
        Text(
            text = type.replaceFirstChar { it.uppercase() },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = pokemonType.textColor,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun InfoCard(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Color(0xFF666666),
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF333333),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
