# Restaurant API

A REST API for restaurant food management built with Java and Spring Boot. This project was developed to practice
RESTful design, relational persistence, entity relationships, validation, and integration testing.

## Technologies

- Java 26
- Spring Boot and Spring Web
- Spring Data JPA / Hibernate
- Jakarta Validation
- MySQL
- Docker and Docker Compose
- Gradle
- JUnit and MockMvc
- H2 Database (integration tests)

## Features

- Register, retrieve, and delete foods
- Search foods by name or UUID
- Filter foods by maximum price
- Order foods by selected properties
- Generate summaries of registered foods
- Associate foods with predefined categories
- Persist food-category relationships using a foreign key
- Validate incoming requests and handle custom exceptions
- Run integration tests with JUnit, MockMvc, and H2

## Food and category relationship

Foods are stored in a `foods` table and categories in a `categories` table. Each food belongs to one category, while a
category can be shared by many foods (`@ManyToOne`). The `foods.category_id` foreign key references `categories.id`.

Categories are represented by a `CategoryType` enum:

- `FAST_FOOD`
- `HEALTHY`
- `DESSERT`
- `DRINK`

A Spring Boot `CommandLineRunner` initializes any missing predefined categories in the database. Existing categories are
reused rather than duplicated.

Clients provide a category **name**, while the API resolves its corresponding `CategoryEntity` and stores the
relationship using the category ID.

## Example request

```http
POST http://localhost:8080/restaurant/foods
Content-Type: application/json

{
  "name": "Margherita Pizza",
  "price": 32.50,
  "calories": 720.0,
  "quantity": 10,
  "categoryName": "FAST_FOOD"
}
```

Example successful response (`201 Created`):

```json
{
  "id": "e7a53c8b-ebb9-41bd-abe3-042c7e1fba1c",
  "name": "Margherita Pizza",
  "price": 32.50,
  "calories": 720.0,
  "quantity": 10,
  "category": "FAST_FOOD"
}
```

The UUID above is illustrative; IDs are generated when foods are created.

## Base URL

```text
http://localhost:8080/restaurant
```

## Endpoints

| Method   | Endpoint                                           | Description                        |
|----------|----------------------------------------------------|------------------------------------|
| `GET`    | `/restaurant/foods`                                | List all foods                     |
| `GET`    | `/restaurant/foods/{id}`                           | Get a food by UUID                 |
| `POST`   | `/restaurant/foods`                                | Register a food with a category    |
| `DELETE` | `/restaurant/foods/{id}`                           | Remove a food by UUID              |
| `GET`    | `/restaurant/foods/search?name=Margherita%20Pizza` | Search for foods by name           |
| `GET`    | `/restaurant/foods/category/{category}`            | Filter by category                 |
| `GET`    | `/restaurant/foods/filter?maxPrice=30`             | Filter foods by maximum price      |
| `GET`    | `/restaurant/foods/order?sortBy=price`             | Order foods by a selected property |
| `GET`    | `/restaurant/foods/summary`                        | Get a summary of stored foods      |

## Running locally

### Requirements

- Java 26
- Docker and Docker Compose

### 1. Clone the repository

```bash
git clone https://github.com/NeylonEduardo/restaurant-api-spring-boot.git
cd restaurant-api-spring-boot
```

### 2. Create the environment file

Linux or macOS:

```bash
cp .env.example .env
```

Windows PowerShell:

```powershell
Copy-Item .env.example .env
```

Update the example credentials in `.env` with your own local values. **Do not commit this file.**

### 3. Start MySQL

```bash
docker compose up -d
docker compose ps
```

### 4. Configure the application environment

In IntelliJ IDEA, use **Run → Edit Configurations → Environment variables** to configure the variables defined in
`.env`.

If you run the app through Gradle instead of an IntelliJ run configuration, ensure the same required environment
variables are available to that process.

### 5. Run the application

Windows PowerShell:

```powershell
.\gradlew.bat bootRun
```

Linux or macOS:

```bash
./gradlew bootRun
```

The API runs at `http://localhost:8080` by default.

## Running tests

Windows PowerShell:

```powershell
.\gradlew.bat test
```

Linux or macOS:

```bash
./gradlew test
```

Integration tests cover food creation, retrieval, deletion, invalid requests, and missing resources, including category
associations. The test profile uses H2 to avoid modifying the development MySQL database.

## Data storage

Food and category data are persisted in MySQL, running in Docker. A named Docker volume preserves database contents
across application and container restarts.

Stop containers without deleting the database volume:

```bash
docker compose down
```

**Warning:** The following also deletes the Docker Compose volumes and their stored data:

```bash
docker compose down -v
```

## Architecture and concepts practiced

- REST APIs and HTTP response codes
- Controller, use case/application, repository, domain, DTO, and entity separation
- Dependency injection and repository pattern
- Spring Data JPA and relational persistence
- Entity relationships (`@ManyToOne`, `@JoinColumn`) and foreign keys
- UUID identifiers and enum mapping (`@Enumerated`)
- Database initialization with `CommandLineRunner`
- Transactions (`@Transactional`) and lazy loading
- Java records, Streams, lambdas, and Comparator
- Request parameters, `ResponseEntity`, and Jakarta Validation
- Custom exceptions and centralized exception handling
- Docker Compose and environment variables
- Integration tests with JUnit, MockMvc, and H2

## Author

**Neylon Eduardo**

Computer Science student focused on Java, Spring Boot, and backend development.
