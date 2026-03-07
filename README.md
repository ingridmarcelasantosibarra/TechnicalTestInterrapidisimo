## Prueba tecnica interrapidismo

## Tecnologias
- Kotlin
- Jetpack compose
- Material 3
- Navigation compose
- Room
- Retrofit
- Dagger

## Arquitectura
- El proyecto sigue Clean Architecture con separación en tres capas: Data, Domain y Presentation.
- Principals SOLID 
  - Responsabilidad unica
  - Segregacion de interfaces
  - Inyeccion de dependencias - Hilt
- Uso de MVVM + UseCase

### Capa Data
- Se encargada de las llamadas remotas y room database
- Interfaces del repository (llamadas remotas y datos locales)
- Los mappers (toEntity, toDomain) encargados de transformar los datos, con el fin de no exponer los Dtos y entities

### Capa Domain
- Casos de uso (use case), encargados de manejar o realizar validaciones de logica de negocio
- Manejo de modelos de dominio limpios, encargados de proporcionar la informacion como la necesita la lógica de negocio
- Uso o manejo de Resource : encargado de comunicar los estados de UI entre domain y presentation

### Capa presentation
- View models: 
  - En el cual se traduce la data de resource a propiedades limpias del UiState con el fin de desacoplar la UI  
  - Manejo de la data local mediante flow, notificando automaticamente cambios al flow suscrito.
- Diseño de pantalla con jetpack compose
  - Manejando los estados de Ui con collectAsStateWithLifecycle para respetar el ciclo de vida de Android
  - Manejo de Callback en los composables para mantener la UI desacoplada de la navegacion 
- Navegacion Type-Safe: Eliminando rutas de strings manuales, centralizando las rutas en un unico archivo

### Core
- DI : Encargado de gestionar el grafo de dependencias. Los módulos están separados por responsabilidades:
  - NetworkModule : Provee las instancias de OkHttpClient y las interfaces del API
  - DatabaseModule : Provee las instancias de Room y DAOS
  - RepositoryModule : Provee y vincula las interfaces de los repository y sus implementaciones