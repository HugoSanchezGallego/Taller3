# Taller3

## Enlace del repositorio ## Enlace del repositorio --> [https://github.com/HugoSanchezGallego/Taller3.git](https://github.com/HugoSanchezGallego/Taller3.git)

Este proyecto es una aplicación Android construida usando Kotlin y Jetpack Compose. Demuestra el uso de Room para almacenamiento local de bases de datos, SharedPreferences para almacenar datos simples y navegación entre diferentes pantallas.

## Estructura del Proyecto

### `MainActivity.kt`

#### `MainActivity`

- **onCreate**: Inicializa las instancias de `SharedPreferences` y `UserDao`. Establece la vista de contenido a `PantallaPrincipal`.

#### `PantallaPrincipal`

- **Parámetros**:
  - `sharedPreferences`: Instancia de `SharedPreferences` para almacenar datos simples.
  - `userDao`: Instancia de `UserDao` para operaciones de base de datos.
  - `context`: El contexto actual.
  - `onNavigate`: Función lambda para manejar la navegación.

- **Elementos de la UI**:
  - `TextField`: Permite al usuario ingresar su nombre.
  - `Button` ("Guardar Nombre"): Guarda el nombre ingresado en `SharedPreferences` y lo inserta en la base de datos.
  - `Text`: Muestra el nombre guardado.
  - `Button` ("Volver a la pantalla de inicio"): Navega de regreso a la pantalla de inicio.
  - `Button` ("Ir a Ajustes"): Navega a la pantalla de ajustes.
  - `Button` ("Ver Base de Datos"): Navega a la pantalla de la base de datos.

### `DatabaseActivity.kt`

#### `DatabaseActivity`

- **onCreate**: Inicializa la instancia de `UserDao`. Establece la vista de contenido a `PantallaBaseDatos`.

#### `PantallaBaseDatos`

- **Parámetros**:
  - `userDao`: Instancia de `UserDao` para operaciones de base de datos.

- **Elementos de la UI**:
  - `Text`: Muestra la lista de usuarios almacenados en la base de datos.
  - `Button` ("Vaciar Base de Datos"): Elimina todos los usuarios de la base de datos.

### `SettingsActivity.kt`

#### `SettingsActivity`

- **onCreate**: Inicializa la instancia de `SharedPreferences`. Establece la vista de contenido a `PantallaConfiguracion`.

#### `PantallaConfiguracion`

- **Parámetros**:
  - `sharedPreferences`: Instancia de `SharedPreferences` para almacenar datos simples.
  - `onNavigate`: Función lambda para manejar la navegación.

- **Elementos de la UI**:
  - `Button` ("Cambiar fondo a rojo"): Cambia el color de fondo a rojo y lo guarda en `SharedPreferences`.
  - `Button` ("Cambiar fondo a verde"): Cambia el color de fondo a verde y lo guarda en `SharedPreferences`.
  - `Button` ("Cambiar fondo a azul"): Cambia el color de fondo a azul y lo guarda en `SharedPreferences`.
  - `Button` ("Volver al inicio"): Navega de regreso a la pantalla de inicio.

### `StartActivity.kt`

#### `StartActivity`

- **onCreate**: Establece la vista de contenido a `PantallaInicio`.

#### `PantallaInicio`

- **Parámetros**:
  - `onNavigate`: Función lambda para manejar la navegación.

- **Elementos de la UI**:
  - `Text`: Muestra un saludo basado en la hora del día.
  - `Button` ("Ir a la actividad principal"): Navega a la actividad principal.

### `UserDatabase.kt`

#### `User`

- **Campos**:
  - `uid`: Identificador único para el usuario.
  - `nombre`: Nombre del usuario.

#### `UserDao`

- **Métodos**:
  - `getUserById(uid: Int)`: Recupera un usuario por su identificador único.
  - `getAllUsers()`: Recupera todos los usuarios de la base de datos.
  - `insertAll(vararg users: User)`: Inserta uno o más usuarios en la base de datos.
  - `deleteAll()`: Elimina todos los usuarios de la base de datos.

#### `UserDatabase`

- **Métodos Abstractos**:
  - `userDao()`: Devuelve una instancia de `UserDao`.

## Dependencias

- `androidx.core:core-ktx`
- `androidx.lifecycle:lifecycle-runtime-ktx`
- `androidx.activity:activity-compose`
- `androidx.compose.ui:ui`
- `androidx.compose.ui:ui-graphics`
- `androidx.compose.ui:ui-tooling-preview`
- `androidx.compose.material3:material3`
- `androidx.room:room-runtime`
- `androidx.room:room-ktx`
- `org.jetbrains.kotlinx:kotlinx-coroutines-core`
- `org.jetbrains.kotlinx:kotlinx-coroutines-android`
- `junit:junit`
- `androidx.test.ext:junit`
- `androidx.test.espresso:espresso-core`
- `androidx.compose.ui:ui-test-junit4`
- `androidx.compose.ui:ui-tooling`
- `androidx.compose.ui:ui-test-manifest`
