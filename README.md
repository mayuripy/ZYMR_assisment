Overview

The Movie API allows users to:

Add new movies

Retrieve all movies

Retrieve a movie by ID

Update movie details

Delete movies

Includes validation for duplicate titles per release year and auto-generated IDs.

Features

CRUD Operations for movies

Validation for duplicates & invalid inputs

HTTP Status Codes for proper responses

Unit Testing for repository, service, and controller

In-memory testing database (H2)

Technology Stack
Layer	Technology
Backend	Java 17, Spring Boot 3.x
Database	MySQL (production), H2 (tests)
ORM	Spring Data JPA (Hibernate)
Testing	JUnit 5, Mockito, MockMvc
JSON	Jackson Databind
Project Structure
src/main/java/com/sit/movies
├─ Controller/      --> MovieController.java
├─ Services/        --> MovieService.java, MovieServiceImpl.java
├─ Model/           --> Movie.java
├─ Repository/      --> MovieRepository.java
├─ MovieApplication.java
└─ resources/
   └─ application.properties (MySQL config)

src/test/java/com/sit/movies
├─ MovieRepositoryTests.java
├─ MovieServiceTests.java
└─ MovieControllerTests.java

Setup & Installation
1. Clone Repository
git clone <your-repo-url>
cd movie-api

2. Configure MySQL Database

Create database movie_db

Update src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/movie_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

3. Build & Run Application
mvn clean install
mvn spring-boot:run


Application runs at: http://localhost:8080/api/movies

API Endpoints
Method	Endpoint	Description
POST	/api/movies	Create a new movie
GET	/api/movies	Get all movies
GET	/api/movies/{id}	Get movie by ID
PUT	/api/movies/{id}	Update movie by ID
DELETE	/api/movies/{id}	Delete movie by ID
Sample Requests & Responses
Create Movie (POST /api/movies)

Request Body:

{
  "title": "Inception",
  "director": "Christopher Nolan",
  "releaseYear": 2010,
  "genre": "Sci-Fi",
  "rating": 9
}


Response:

{
  "id": 1,
  "title": "Inception",
  "director": "Christopher Nolan",
  "releaseYear": 2010,
  "genre": "Sci-Fi",
  "rating": 9
}

Get All Movies (GET /api/movies)

Response:

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

Testing
1. Dependencies (pom.xml)
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

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>
