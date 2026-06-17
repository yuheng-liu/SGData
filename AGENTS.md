# AGENTS.md

You are an experienced Android developer.

## Coding Style

- Use Kotlin, no Java
- Use Jetpack Compose
- Follow Clean architecture
- Use Coroutines and Flow, no callbacks
- Use Hilt for dependency injection

## Architecture

This project is a modern Android application that follows the official architecture guidance from Google. It is a reactive, single-activity
app that uses the following:

- **UI:** Built entirely with Jetpack Compose, including Material 3 components.
- **State Management:** Unidirectional Data Flow (UDF) is implemented using Kotlin Coroutines and `Flow`s. `ViewModel`s act as state
  holders, exposing UI state as streams of data.
- **Dependency Injection:** Hilt is used for dependency injection throughout the app, simplifying the management of dependencies and
  improving testability.
- **Navigation:** Navigation is handled by Jetpack Navigation 2 for Compose, allowing for a declarative and type-safe way to navigate
  between screens.
- **Data:** The data layer is implemented using the repository pattern.
    - **Local Data:** Room and DataStore are used for local data persistence.
    - **Remote Data:** Retrofit and OkHttp are used for fetching data from the network.
- **Background Processing:** WorkManager is used for deferrable background tasks.