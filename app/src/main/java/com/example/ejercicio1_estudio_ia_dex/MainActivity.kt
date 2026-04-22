package com.example.ejercicio1_estudio_ia_dex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ejercicio1_estudio_ia_dex.presentation.NavRoutes
import com.example.ejercicio1_estudio_ia_dex.presentation.detail.PokemonDetailScreen
import com.example.ejercicio1_estudio_ia_dex.presentation.detail.PokemonDetailViewModel
import com.example.ejercicio1_estudio_ia_dex.presentation.detail.PokemonDetailViewModelFactory
import com.example.ejercicio1_estudio_ia_dex.presentation.list.PokemonListScreen
import com.example.ejercicio1_estudio_ia_dex.presentation.list.PokemonListViewModel
import com.example.ejercicio1_estudio_ia_dex.ui.theme.Ejercicio1EstudioIADEXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio1EstudioIADEXTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = NavRoutes.POKEMON_LIST
                    ) {
                        composable(route = NavRoutes.POKEMON_LIST) {
                            val listViewModel: PokemonListViewModel = viewModel()
                            PokemonListScreen(
                                viewModel = listViewModel,
                                onPokemonClick = { pokemonId ->
                                    navController.navigate("${NavRoutes.POKEMON_DETAIL}/$pokemonId")
                                }
                            )
                        }

                        composable(
                            route = "${NavRoutes.POKEMON_DETAIL}/{pokemonId}",
                            arguments = listOf(
                                navArgument("pokemonId") {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            val detailViewModel: PokemonDetailViewModel = viewModel(
                                factory = PokemonDetailViewModelFactory(
                                    backStackEntry.savedStateHandle
                                )
                            )

                            PokemonDetailScreen(
                                viewModel = detailViewModel,
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
