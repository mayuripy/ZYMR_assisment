
# Movie API

Movie API is a Spring Boot RESTful service for managing movies. It provides full CRUD operations, validation for duplicates, proper HTTP responses, and unit testing with an in-memory database for testing purposes.

# Features

Full CRUD operations for movies (Create, Read, Update, Delete)

Validation for duplicate movie titles per release year

Auto-generated movie IDs

Proper HTTP status codes for responses

Unit testing for repository, service, and controller layers

In-memory H2 database for testing purposes

# Technology Stack
Layer	Technology
Backend	Java 17, Spring Boot 3.x
Database	MySQL (production), H2 (tests)
ORM	Spring Data JPA (Hibernate)
Testing	JUnit 5, Mockito, MockMvc
JSON	Jackson Databind


# Database 

  Configure MySQL Database

   CREATE DATABASE movie_db;


Update application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/movie_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect


Build & Run Application

mvn clean install
mvn spring-boot:run


Application URL: http://localhost:8087/api/movies

# API Endpoints
Method	Endpoint	Description
POST	/api/movies	Create a new movie
GET	/api/movies	Get all movies
GET	/api/movies/{id}	Get movie by ID
PUT	/api/movies/{id}	Update movie by ID
DELETE	/api/movies/{id}	Delete movie by ID

# Sample Requests & Responses
Create Movie (POST /api/movies)

Request Body

{
  "title": "Inception",
  "director": "Christopher Nolan",
  "releaseYear": 2010,
  "genre": "Sci-Fi",
  "rating": 9
}


Response

{
  "id": 1,
  "title": "Inception",
  "director": "Christopher Nolan",
  "releaseYear": 2010,
  "genre": "Sci-Fi",
  "rating": 9
}

Get All Movies (GET /api/movies)

Response

[
  {
    "id": 1,
    "title": "Inception",
    "director": "Christopher Nolan",
    "releaseYear": 2010,
    "genre": "Sci-Fi",
    "rating": 9
  }
]

# Testing

Test Dependencies (pom.xml)

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>


