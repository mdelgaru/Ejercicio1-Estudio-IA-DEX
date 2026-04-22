package com.example.ejercicio1_estudio_ia_dex.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.ejercicio1_estudio_ia_dex.domain.model.Pokemon
import com.example.ejercicio1_estudio_ia_dex.presentation.common.ErrorModal

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel,
    onPokemonClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold() { insets ->
        Box(modifier = Modifier.fillMaxSize().padding(insets)) {
            when {
                state.isLoading && state.items.isEmpty() -> {
                    LoadingView()
                }
                state.items.isEmpty() && state.error == null -> {
                    EmptyView()
                }
                else -> {
                    PokemonList(
                        items = state.items,
                        onPokemonClick = onPokemonClick,
                        onLoadMore = { viewModel.loadNextPage() },
                        isLoadingMore = state.isLoading
                    )
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
}

@Composable
private fun PokemonList(
    items: List<Pokemon>,
    onPokemonClick: (Int) -> Unit,
    onLoadMore: () -> Unit,
    isLoadingMore: Boolean
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header fijo en la parte superior
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFDC0A2D))
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "Pokédex",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // LazyColumn que se desplaza debajo del header
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            itemsIndexed(items) { index, pokemon ->
                if (index == items.lastIndex && !isLoadingMore) {
                    // Cuando se alcanza el final, pedir más
                    onLoadMore()
                }
                PokemonItem(
                    pokemon = pokemon,
                    onClick = { onPokemonClick(pokemon.id) }
                )
            }

            if (isLoadingMore) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Composable
private fun PokemonItem(
    pokemon: Pokemon,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(pokemon.imageUrl),
                contentDescription = pokemon.name,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "#${(pokemon.id.toString().padStart(4, '0'))} - ${pokemon.name}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )
    }
}

@Composable
private fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


@Composable
private fun EmptyView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No hay Pokémon disponibles")
    }
}
