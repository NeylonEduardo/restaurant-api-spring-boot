# Restaurant API

A REST API for restaurant food management, developed to practice Java and Spring Boot.

## Technologies

* Java
* Spring Boot
* Spring Web
* Jakarta Validation
* Gradle

## Features

* Register and remove foods
* Find foods by ID or name
* Filter foods by maximum price
* Order foods by different properties
* Generate a food summary
* Validate request data
* Handle custom exceptions

## Food structure

```json
{
  "name": "Margherita Pizza",
  "quantity": 8,
  "price": 32.50,
  "calories": 720.0,
  "id": 1
}
```

## Base URL

All endpoints use the following base path:

```text
http://localhost:8080/restaurant
```

The `/restaurant` prefix is defined in the controller:

```java

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    // Endpoints
}
```

## Endpoints

| Method   | Endpoint                               | Description                           |
|----------|----------------------------------------|---------------------------------------|
| `GET`    | `/restaurant/foods`                    | Returns all foods                     |
| `GET`    | `/restaurant/foods/{id}`               | Returns a food by ID                  |
| `POST`   | `/restaurant/foods`                    | Registers a new food                  |
| `DELETE` | `/restaurant/foods/{id}`               | Removes a food by ID                  |
| `GET`    | `/restaurant/foods/search?name=pizza`  | Searches for foods by name            |
| `GET`    | `/restaurant/foods/filter?maxPrice=30` | Filters foods by maximum price        |
| `GET`    | `/restaurant/foods/order?sortBy=price` | Orders foods by the selected property |
| `GET`    | `/restaurant/foods/summary`            | Returns a summary of the stored foods |

## Running the project

Clone the repository and enter its directory:

```bash
git clone https://github.com/NeylonEduardo/restaurant-api-spring-boot
cd restaurant-api-spring-boot
```

Run on Windows:

```powershell
.\gradlew.bat bootRun
```

Run on Linux or macOS:

```bash
./gradlew bootRun
```

The API will be available at:

```text
http://localhost:8080
```

## Data storage

The project currently stores data in memory using an `ArrayList`. All registered data is lost when the application is
restarted.

## Concepts practiced

* REST APIs
* Controller and Service layers
* Dependency injection
* Java records
* Streams and lambdas
* Comparator
* Request parameters
* ResponseEntity
* Data validation
* Custom exception handling

## Author

**Neylon Eduardo**

Computer Science student focused on Java, Spring Boot and backend development.
