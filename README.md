
Movie API

Movie API is a Spring Boot RESTful service for managing movies. It provides full CRUD operations, validation for duplicates, proper HTTP responses, and unit testing with an in-memory database for testing purposes.

🎬 Overview

The Movie API allows users to:

Add new movies

Retrieve all movies

Retrieve a movie by ID

Update movie details

Delete movies

Validation Features:

Duplicate movie titles per release year are not allowed

Auto-generated movie IDs

🚀 Features

CRUD operations for movies

Validation for duplicate titles and invalid inputs

Proper HTTP status codes for responses

Unit testing for repository, service, and controller layers

In-memory H2 database for testing

🏗️ Technology Stack
Layer	Technology
Backend	Java 17, Spring Boot 3.x
Database	MySQL (production), H2 (tests)
ORM	Spring Data JPA (Hibernate)
Testing	JUnit 5, Mockito, MockMvc
JSON	Jackson Databind
🗂️ Project Structure
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

📦 Setup & Installation
1. Clone Repository
git clone <your-repo-url>
cd movie-api

2. Configure MySQL Database

Create a database: movie_db

Update src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/movie_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

3. Build & Run Application
mvn clean install
mvn spring-boot:run


Application URL: http://localhost:8087/api/movies

📡 API Endpoints
Method	Endpoint	Description
POST	/api/movies	Create a new movie
GET	/api/movies	Get all movies
GET	/api/movies/{id}	Get movie by ID
PUT	/api/movies/{id}	Update movie by ID
DELETE	/api/movies/{id}	Delete movie by ID
📝 Sample Requests & Responses
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

🧪 Testing
1. Test Dependencies (pom.xml)
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

2. Run Tests
mvn test


Tests include repository, service, and controller layers.

Uses H2 in-memory database for isolated testing.

📚 Contributions

Contributions are welcome!

Fork the repository

Create your feature branch (git checkout -b feature/your-feature)

Commit your changes (git commit -m 'Add feature')

Push to the branch (git push origin feature/your-feature)

Open a Pull Request
