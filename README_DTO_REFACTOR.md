# Refactorización de DTOs y Value Objects (Clean Architecture & DDD)

## Objetivo
Reorganizar los DTOs, Value Objects y Mappers de la aplicación siguiendo los principios de Clean Architecture y Domain Driven Design (DDD), separando responsabilidades y mejorando la mantenibilidad del código.

## Estructura de Carpetas
```
src/main/java/com/acueducto/arpa/
│
├── domain/
│   ├── model/
│   │   ├── dtos/           # DTOs de dominio
│   │   └── vo/             # Value Objects
│   ├── ports/              # Interfaces de repositorios
│   └── service/            # Servicios de dominio
│
├── application/
│   └── handler/
│       └── dtos/
│           ├── request/    # DTOs para peticiones HTTP
│           └── response/   # DTOs para respuestas HTTP
│
├── infrastructure/
│   └── adapter/
│       └── persistence/
│           ├── entity/     # Entidades JPA
│           └── mapper/     # Mappers entre entidades y DTOs
```

## Principios Aplicados
- **Single Responsibility Principle:** Cada DTO/VO tiene una única responsabilidad.
- **Dependency Inversion:** Las capas internas no dependen de las externas.
- **Inmutabilidad:** Uso de `record` y clases `final` para DTOs y VOs.
- **Validación:**
  - Bean Validation en DTOs de aplicación.
  - Validación de dominio en constructores de VOs.
- **Mappers:** Centralizados para conversión entre capas.

## Value Objects Implementados
- `Name`, `Serial`, `Comment`, `Make` (inmutables, validación en constructor)
- `ArticleStatus` (enum en dominio)

## Ejemplo de Conversión
- Entity <-> Domain DTO <-> Application DTO (request/response) usando mappers.

## Eliminación de DTOs Antiguos
- Se eliminaron los DTOs antiguos de las carpetas `model` y `rest/dto`.

## Compatibilidad
- Se mantuvo la compatibilidad con la API existente, minimizando breaking changes.

## Recomendaciones
- Usar los nuevos DTOs y mappers en controladores y servicios.
- Seguir el patrón para nuevos casos de uso.

---
_Refactorización realizada siguiendo Clean Architecture y DDD para mayor claridad, mantenibilidad y escalabilidad._ 