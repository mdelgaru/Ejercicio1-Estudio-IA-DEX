# 🎮 Pokédex App - Android

Aplicación Android nativa desarrollada en Kotlin que consume la API de PokéAPI para mostrar un listado de Pokémon con paginación y detalles individuales.

## 📋 Descripción

Esta aplicación es un ejercicio de desarrollo móvil que implementa una Pokédex funcional siguiendo los principios de Clean Architecture. Permite explorar el mundo Pokémon navegando por un listado paginado y consultando información detallada de cada Pokémon.

## ✨ Características Principales

- 📱 Interfaz moderna desarrollada con Jetpack Compose
- 🏗️ Arquitectura Clean Architecture (Data, Domain, Presentation)
- 🌐 Consumo de API REST (PokéAPI)
- 📄 Paginación eficiente (20 pokémon por página)
- 🔄 Gestión de estados (Loading, Success, Error, Empty)
- 🎨 Diseño inspirado en la Pokédex clásica
- 🚀 Navegación fluida entre pantallas
- ⚠️ Manejo robusto de errores sin crashes
- 🎭 Badges de tipos con colores oficiales de Pokémon

## 🛠️ Tecnologías y Dependencias

### Core
- **Kotlin** - Lenguaje de programación principal
- **Jetpack Compose** - Framework UI moderno y declarativo
- **Material Design 3** - Sistema de diseño

### Arquitectura y Componentes
- **ViewModel** - Gestión de estado y lógica de UI
- **Coroutines** - Programación asíncrona
- **LiveData/StateFlow** - Observables para datos reactivos
- **Navigation Compose** - Navegación entre pantallas

### Networking
- **Retrofit 2** - Cliente HTTP type-safe
- **Gson** - Serialización/deserialización JSON
- **OkHttp Logging Interceptor** - Debug de peticiones HTTP

### Imágenes
- **Coil** - Carga asíncrona de imágenes optimizada para Compose

### Inyección de Dependencias
- **Manual DI** - Implementación manual con módulos Kotlin

## 📦 Requisitos Previos

- Android Studio Hedgehog (2023.1.1) o superior
- JDK 11 o superior
- SDK de Android:
  - `minSdk`: 24 (Android 7.0 Nougat)
  - `targetSdk`: 36
  - `compileSdk`: 36
- Conexión a Internet (para consumir la API)

## 🚀 Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/mdelgaru/Ejercicio1-Estudio-IA-DEX.git
   cd Ejercicio1-Estudio-IA-DEX
   ```

2. **Abrir en Android Studio**
   - File → Open → Seleccionar la carpeta del proyecto

3. **Sincronizar dependencias**
   - Android Studio sincronizará automáticamente Gradle
   - O ejecutar manualmente: `./gradlew build`

4. **Ejecutar la aplicación**
   - Conectar un dispositivo Android o iniciar un emulador
   - Presionar el botón "Run" (▶️) o `Shift + F10`

## 📁 Estructura del Proyecto

```
app/src/main/java/com/example/ejercicio1_estudio_ia_dex/
│
├── data/                           # Capa de Datos
│   ├── di/
│   │   └── NetworkModule.kt        # Configuración Retrofit/OkHttp
│   ├── remote/
│   │   ├── api/
│   │   │   └── PokemonApi.kt       # Definición endpoints API
│   │   ├── dto/
│   │   │   ├── PokemonRequest.kt   # DTOs para requests
│   │   │   └── PokemonResponse.kt  # DTOs para responses
│   │   └── mapper/
│   │       └── PokemonMapper.kt    # Mapeo DTO ↔ Domain
│   └── repository/
│       └── PokemonRepositoryImpl.kt # Implementación del repositorio
│
├── domain/                         # Capa de Dominio
│   ├── model/
│   │   ├── Pokemon.kt              # Entidad Pokémon (listado)
│   │   └── PokemonDetail.kt        # Entidad Pokémon (detalle)
│   ├── repository/
│   │   └── PokemonRepository.kt    # Interfaz del repositorio
│   └── usecase/
│       ├── GetPokemonListUseCase.kt    # Caso de uso: obtener listado
│       └── GetPokemonDetailUseCase.kt  # Caso de uso: obtener detalle
│
├── presentation/                   # Capa de Presentación
│   ├── common/
│   │   ├── UiState.kt              # Estados genéricos de UI
│   │   └── ErrorModal.kt           # Modal reutilizable de error
│   ├── list/
│   │   ├── PokemonListScreen.kt    # Pantalla de listado
│   │   ├── PokemonListViewModel.kt # ViewModel del listado
│   │   └── PokemonListState.kt     # Estado del listado
│   └── detail/
│       ├── PokemonDetailScreen.kt      # Pantalla de detalle
│       ├── PokemonDetailViewModel.kt   # ViewModel del detalle
│       ├── PokemonDetailState.kt       # Estado del detalle
│       └── PokemonDetailViewModelFactory.kt # Factory del ViewModel
│
├── ui/theme/                       # Tema de la aplicación
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
│
└── MainActivity.kt                 # Activity principal
```

## 🎯 Funcionalidades Implementadas

### ✅ Requisitos Mínimos Cumplidos

#### Consumo de API
- ✅ GET `https://pokeapi.co/api/v2/pokemon?limit=&offset=`
- ✅ GET `https://pokeapi.co/api/v2/pokemon/{id}` para detalles

#### Estados de UI
- ✅ **Loading**: Indicador de carga circular
- ✅ **Error**: Modal con mensaje y botón de reintento
- ✅ **Empty**: Mensaje cuando no hay datos
- ✅ **Success**: Visualización de datos

#### Paginación
- ✅ Mínimo 3 páginas disponibles (20 pokémon por página)
- ✅ Navegación con botones "Anterior" y "Siguiente"
- ✅ Deshabilitar botón "Anterior" en primera página

#### Item de Listado
- ✅ Imagen del Pokémon (sprite)
- ✅ Nombre capitalizado
- ✅ ID/Índice visible (formato #0001)
- ✅ Diseño tipo Pokédex clásica

#### Pantalla de Detalle
- ✅ Nombre del Pokémon
- ✅ ID (#0001)
- ✅ Sprite oficial
- ✅ Tipos (con badges de colores)
- ✅ Altura (en metros)
- ✅ Peso (en kilogramos)
- ✅ Botón de regreso a la lista

#### Navegación y Arquitectura
- ✅ Navegación entre listado y detalle
- ✅ Clean Architecture implementada
- ✅ Separación de capas (Data, Domain, Presentation)
- ✅ Manejo de errores sin crashes

## 🎨 Diseño de Pantallas

### Pantalla de Listado
- **Header**: Fondo rojo con título "Pokédex"
- **Grid**: Cuadrícula de 2 columnas con cards
- **Card**: Imagen, nombre e ID del Pokémon
- **Footer**: Botones de paginación

### Pantalla de Detalle
- **Header**: Fondo rojo con botón atrás y nombre/ID
- **Body**: Card blanca con información del Pokémon
  - Imagen central
  - Badges de tipos con colores oficiales
  - Información de peso y altura

### Colores de Tipos de Pokémon
Los badges de tipos utilizan la paleta de colores oficial:
- 🔥 Fire (Fuego): #F08030
- 💧 Water (Agua): #6890F0
- 🌱 Grass (Planta): #78C850
- ⚡ Electric (Eléctrico): #F8D030
- 🧊 Ice (Hielo): #98D8D8
- 👊 Fighting (Lucha): #C03028
- ☠️ Poison (Veneno): #A040A0
- 🌍 Ground (Tierra): #E0C068
- 🦅 Flying (Volador): #A890F0
- 🔮 Psychic (Psíquico): #F85888
- 🐛 Bug (Bicho): #A8B820
- 🪨 Rock (Roca): #B8A038
- 👻 Ghost (Fantasma): #705898
- 🐉 Dragon (Dragón): #7038F8
- 🌑 Dark (Siniestro): #705848
- ⚙️ Steel (Acero): #B8B8D0
- 🧚 Fairy (Hada): #EE99AC
- 🔮 Normal: #A8A878

## 🌐 API Utilizada

**PokéAPI**: [https://pokeapi.co/](https://pokeapi.co/)

### Endpoints Consumidos

1. **Listado de Pokémon**
   ```
   GET https://pokeapi.co/api/v2/pokemon?limit=20&offset=0
   ```
   - Parámetros:
     - `limit`: Cantidad de pokémon por página
     - `offset`: Desplazamiento para paginación

2. **Detalle de Pokémon**
   ```
   GET https://pokeapi.co/api/v2/pokemon/{id}
   ```
   - Parámetro:
     - `id`: ID del Pokémon (1-1025+)

## 🏗️ Arquitectura Clean

### Capas

1. **Data (Datos)**
   - Responsable de obtener datos de fuentes externas (API)
   - Contiene implementaciones concretas
   - Mapea DTOs a entidades de dominio

2. **Domain (Dominio)**
   - Núcleo de la aplicación
   - Contiene lógica de negocio
   - Independiente de frameworks
   - Define contratos (interfaces)

3. **Presentation (Presentación)**
   - UI y ViewModels
   - Observa estados del dominio
   - Maneja eventos de usuario
   - Jetpack Compose para UI declarativa

### Flujo de Datos

```
UI (Compose) → ViewModel → UseCase → Repository → API
                  ↑                                   ↓
                  ←←←←←←← StateFlow ←←←←←←←←←←←←←←←←←←
```

## 🧪 Testing

El proyecto incluye estructura base para testing:

- **Unit Tests**: `app/src/test/`
- **Instrumentation Tests**: `app/src/androidTest/`

Para ejecutar los tests:
```bash
./gradlew test           # Tests unitarios
./gradlew connectedTest  # Tests instrumentados
```

## 📝 Mejoras Futuras

- [ ] Implementar búsqueda de Pokémon por nombre
- [ ] Añadir filtros por tipo
- [ ] Implementar caché local con Room
- [ ] Agregar favoritos
- [ ] Incluir información de evoluciones
- [ ] Soporte para modo oscuro
- [ ] Animaciones de transición
- [ ] Tests unitarios y de integración
- [ ] Inyección de dependencias con Hilt/Dagger

## 👤 Autor

**Miguel Delgado**

- GitHub: [@mdelgaru](https://github.com/mdelgaru)
- Proyecto: [Ejercicio1-Estudio-IA-DEX](https://github.com/mdelgaru/Ejercicio1-Estudio-IA-DEX)

## 📄 Licencia

Este proyecto es un ejercicio educativo y está disponible para uso académico.

## 🙏 Agradecimientos

- [PokéAPI](https://pokeapi.co/) por proporcionar la API gratuita
- Comunidad de Jetpack Compose
- The Pokémon Company por la inspiración

---

**Desarrollado con ❤️ usando Kotlin y Jetpack Compose**
