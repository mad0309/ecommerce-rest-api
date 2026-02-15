# E-Commerce REST API

API REST completa para una tienda online con gestion de productos, usuarios, pedidos y pagos. Construida con **arquitectura hexagonal**

## Tech Stack

- **Java 17**
- **Spring Boot 4.0.2** (Spring Framework 7)
- **Spring Security 7** + JWT (jjwt 0.12.6)
- **Spring Data JPA** + Hibernate
- **H2 Database** (en memoria)
- **SpringDoc OpenAPI** (Swagger UI)
- **MapStruct** (mapeo entre capas)
- **Lombok**
- **Gradle**

## Arquitectura Hexagonal

Cada modulo sigue la estructura:

```
modulo/
├── domain/                    # Nucleo de negocio (sin dependencias externas)
│   ├── model/                 # Entidades de dominio
│   ├── port/
│   │   ├── in/                # Puertos de entrada (casos de uso)
│   │   └── out/               # Puertos de salida (repositorios)
│   └── exception/             # Excepciones de dominio
├── application/
│   └── service/               # Implementacion de casos de uso
└── infrastructure/
    ├── adapter/
    │   ├── in/                # Adaptadores de entrada (controllers)
    │   └── out/               # Adaptadores de salida (JPA repositories)
    └── mapper/                # MapStruct mappers
```

### Modulos

| Modulo | Descripcion |
|--------|-------------|
| `user` | Gestion de usuarios (CRUD, roles) |
| `auth` | Autenticacion y autorizacion (JWT, login, registro) |
| `product` | Catalogo de productos (CRUD, categorias, paginacion) |
| `order` | Gestion de pedidos (crear, listar, estados) |
| `payment` | Procesamiento de pagos (simulado) |

## Requisitos

- **Java 17** o superior
- **Gradle 8+** (incluido via wrapper)

## Instalacion y Ejecucion

```bash
# Clonar el repositorio
git clone <repository-url>
cd ecommerce-rest-api

# Compilar el proyecto
./gradlew clean build

# Ejecutar la aplicacion
./gradlew bootRun
```

La aplicacion se ejecuta en `http://localhost:8080`.

## Documentacion de la API

Una vez ejecutada la aplicacion, accede a:

- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)
- **OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## Endpoints

### Authentication (`/api/v1/auth`)

| Metodo | Endpoint | Descripcion | Acceso |
|--------|----------|-------------|--------|
| POST | `/api/v1/auth/register` | Registrar nuevo usuario | Publico |
| POST | `/api/v1/auth/login` | Iniciar sesion (retorna JWT) | Publico |

### Users (`/api/v1/users`)

| Metodo | Endpoint | Descripcion | Acceso |
|--------|----------|-------------|--------|
| GET | `/api/v1/users` | Listar todos los usuarios | ADMIN |
| GET | `/api/v1/users/{id}` | Obtener usuario por ID | ADMIN |
| PUT | `/api/v1/users/{id}` | Actualizar usuario | ADMIN |
| DELETE | `/api/v1/users/{id}` | Eliminar usuario | ADMIN |

### Products (`/api/v1/products`)

| Metodo | Endpoint | Descripcion | Acceso |
|--------|----------|-------------|--------|
| GET | `/api/v1/products` | Listar productos (paginado) | Publico |
| GET | `/api/v1/products/{id}` | Obtener producto por ID | Publico |
| GET | `/api/v1/products/category/{category}` | Filtrar por categoria | Publico |
| POST | `/api/v1/products` | Crear producto | ADMIN |
| PUT | `/api/v1/products/{id}` | Actualizar producto | ADMIN |
| DELETE | `/api/v1/products/{id}` | Eliminar producto | ADMIN |

**Categorias disponibles**: `ELECTRONICS`, `CLOTHING`, `BOOKS`, `HOME`, `SPORTS`, `FOOD`, `OTHER`

### Orders (`/api/v1/orders`)

| Metodo | Endpoint | Descripcion | Acceso |
|--------|----------|-------------|--------|
| GET | `/api/v1/orders` | Listar pedidos del usuario | Autenticado |
| GET | `/api/v1/orders/{id}` | Obtener pedido por ID | Autenticado |
| POST | `/api/v1/orders` | Crear pedido | Autenticado |
| PUT | `/api/v1/orders/{id}/status` | Actualizar estado del pedido | ADMIN |

**Estados de pedido**: `PENDING`, `CONFIRMED`, `SHIPPED`, `DELIVERED`, `CANCELLED`

### Payments (`/api/v1/payments`)

| Metodo | Endpoint | Descripcion | Acceso |
|--------|----------|-------------|--------|
| POST | `/api/v1/payments` | Procesar pago | Autenticado |
| GET | `/api/v1/payments/{id}` | Obtener pago por ID | Autenticado |
| GET | `/api/v1/payments/order/{orderId}` | Obtener pago por pedido | Autenticado |

**Metodos de pago**: `CREDIT_CARD`, `DEBIT_CARD`, `PAYPAL`, `BANK_TRANSFER`

## Uso con cURL

### 1. Registrar un usuario

```bash
curl -X POST http://localhost:8080/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Martin",
    "lastName": "Alvarado",
    "email": "martin@example.com",
    "password": "password123"
  }'
```

### 2. Iniciar sesion

```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "martin@example.com",
    "password": "password123"
  }'
```

Respuesta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "email": "martin@example.com",
  "role": "USER"
}
```

### 3. Listar productos

```bash
curl http://localhost:8080/api/v1/products?page=0&size=10
```

### 4. Crear un pedido

```bash
curl -X POST http://localhost:8080/api/v1/orders \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN>" \
  -d '{
    "items": [
      {"productId": 1, "quantity": 1},
      {"productId": 4, "quantity": 2}
    ],
    "shippingAddress": "Calle Principal 123, Lima, Peru"
  }'
```

### 5. Procesar pago

```bash
curl -X POST http://localhost:8080/api/v1/payments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN>" \
  -d '{
    "orderId": 1,
    "paymentMethod": "CREDIT_CARD"
  }'
```

## Base de Datos

El proyecto usa **H2** en memoria con datos de prueba precargados.

- **H2 Console**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL**: `jdbc:h2:mem:ecommercedb`
- **User**: `sa`
- **Password**: *(vacio)*

### Datos precargados

- 3 usuarios (1 admin + 2 users)
- 8 productos en distintas categorias

| Email | Password | Rol |
|-------|----------|-----|
| admin@ecommerce.com | password123 | ADMIN |
| john@example.com | password123 | USER |
| jane@example.com | password123 | USER |

## Seguridad

- Autenticacion stateless con **JWT** (JSON Web Tokens)
- Tokens con expiracion de 24 horas
- Passwords encriptados con **BCrypt**
- Control de acceso basado en roles (**RBAC**): `ADMIN` y `USER`
- Endpoints publicos: auth, productos (lectura), swagger
- CSRF deshabilitado (API REST stateless)

## Estructura del Proyecto

```
src/main/java/com/mad0309/ecommercerestapi/
├── EcommerceRestApiApplication.java
├── common/infrastructure/config/
│   ├── OpenApiConfig.java
│   └── GlobalExceptionHandler.java
├── auth/
├── user/
├── product/
├── order/
└── payment/
```

## Autor

**MAD0309**
