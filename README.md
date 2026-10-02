# Restaurant API

A REST API for restaurant food management, developed to practice Java, Spring Boot and relational database persistence.

## Technologies

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Jakarta Validation
- MySQL
- Docker
- Docker Compose
- Gradle

## Features

- Register and remove foods
- Find foods by ID or name
- Filter foods by maximum price
- Order foods by different properties
- Generate a food summary
- Validate request data
- Handle custom exceptions
- Persist data in MySQL

## Food structure

```json
{
  "name": "Margherita Pizza",
  "price": 32.50,
  "calories": 720.0,
  "quantity": 8
}
```

## Base URL

All endpoints use the following base path:

```text
http://localhost:8080/restaurant
```

## Endpoints

| Method   | Endpoint                                           | Description                           |
|----------|----------------------------------------------------|---------------------------------------|
| `GET`    | `/restaurant/foods`                                | Returns all foods                     |
| `GET`    | `/restaurant/foods/{id}`                           | Returns a food by ID                  |
| `POST`   | `/restaurant/foods`                                | Registers a new food                  |
| `DELETE` | `/restaurant/foods/{id}`                           | Removes a food by ID                  |
| `GET`    | `/restaurant/foods/search?name=Margherita%20Pizza` | Searches for a food by name           |
| `GET`    | `/restaurant/foods/filter?maxPrice=30`             | Filters foods by maximum price        |
| `GET`    | `/restaurant/foods/order?sortBy=price`             | Orders foods by the selected property |
| `GET`    | `/restaurant/foods/summary`                        | Returns a summary of the stored foods |

## Running locally

### Requirements

- Java 26
- Docker
- Docker Compose

### 1. Clone the repository

```bash
git clone https://github.com/NeylonEduardo/restaurant-api-spring-boot.git
cd restaurant-api-spring-boot
```

### 2. Create the environment file

On Linux or macOS:

```bash
cp .env.example .env
```

On Windows PowerShell:

```powershell
Copy-Item .env.example .env
```

Open `.env` and replace the example passwords with local values.

The `.env` file is ignored by Git and must not be committed.

### 3. Start MySQL

```bash
docker compose up -d
```

Check the container status:

```bash
docker compose ps
```

### 4. Configure the application environment

When using IntelliJ IDEA, configure the Spring Boot Run Configuration to load the `.env` file:

```text
Run → Edit Configurations → Environment variables
```

Select the `.env` file created in the project root.

### 5. Run the application

On Windows:

```powershell
.\gradlew.bat bootRun
```

On Linux or macOS:

```bash
./gradlew bootRun
```

The API will be available at:

```text
http://localhost:8080
```

## Data storage

The API stores food data in a MySQL database running inside a Docker container.

The database uses a named Docker volume, so registered data is preserved when the container or application is restarted.

To stop the database:

```bash
docker compose down
```

Removing the volume will permanently delete the local database data:

```bash
docker compose down -v
```

## Concepts practiced

- REST APIs
- Layered architecture
- Controller and Service layers
- Dependency injection
- Spring Data JPA
- Relational database persistence
- Repository pattern
- Java records
- DTOs
- Streams and lambdas
- Comparator
- Request parameters
- ResponseEntity
- Data validation
- Custom exception handling
- Docker Compose
- Environment variables

## Author

**Neylon Eduardo**

Computer Science student focused on Java, Spring Boot and backend development.