package com.example.ejercicio1_estudio_ia_dex.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorModal(
    errorMessage: String?,
    onDismiss: () -> Unit,
    onRetry: () -> Unit
) {
    AnimatedVisibility(
        visible = errorMessage != null,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    )
                    .padding(24.dp)
            ) {
                // Header con título y botón de cerrar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Ha ocurrido un error",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error
                    )
                    TextButton(onClick = onDismiss) {
                        Text("✕", style = MaterialTheme.typography.headlineMedium)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Mensaje de error personalizado
                Text(
                    text = mapErrorMessage(errorMessage ?: ""),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botón de reintentar
                Button(
                    onClick = {
                        onDismiss()
                        onRetry()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "Reintentar",
                        style = MaterialTheme.typography.labelLarge
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

/**
 * Mapea mensajes de error técnicos a mensajes amigables en español
 */
private fun mapErrorMessage(technicalError: String): String {
    return when {
        technicalError.contains("Unable to resolve host", ignoreCase = true) ||
        technicalError.contains("No address associated with hostname", ignoreCase = true) -> {
            "No se pudo establecer conexión con el servidor. Por favor, verifica tu conexión a internet e inténtalo de nuevo."
        }
        technicalError.contains("timeout", ignoreCase = true) -> {
            "La conexión ha tardado demasiado tiempo. Por favor, verifica tu conexión a internet e inténtalo de nuevo."
        }
        technicalError.contains("network", ignoreCase = true) ||
        technicalError.contains("connection", ignoreCase = true) -> {
            "Hubo un problema con la conexión de red. Por favor, verifica tu conexión a internet e inténtalo de nuevo."
        }
        technicalError.contains("404", ignoreCase = true) -> {
            "No se encontró el recurso solicitado. Por favor, inténtalo de nuevo más tarde."
        }
        technicalError.contains("500", ignoreCase = true) ||
        technicalError.contains("502", ignoreCase = true) ||
        technicalError.contains("503", ignoreCase = true) -> {
            "El servidor no está disponible en este momento. Por favor, inténtalo de nuevo más tarde."
        }
        else -> {
            "Ocurrió un error inesperado. Por favor, inténtalo de nuevo."
        }
    }
}
