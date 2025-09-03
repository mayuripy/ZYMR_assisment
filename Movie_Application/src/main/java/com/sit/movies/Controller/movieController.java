package com.sit.movies.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sit.movies.Model.Movie;
import com.sit.movies.Services.MovieService;

;

@RestController
@RequestMapping("/api/movies")
public class movieController {
  
	@Autowired
	 private MovieService movieService; 
	
		@PostMapping("/PostMovie")
		public ResponseEntity<?> createMovie(@Validated @RequestBody Movie movie) {

			if (movie.getId() != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("ID should not be provided. It will be auto-generated.");
			}

			if (movieService.existsByTitleAndYear(movie.getTitle(), movie.getReleaseYear())) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("Movie with this title and release year already exists.");
			}

			Movie savedMovie = movieService.createMovie(movie);

			return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
		}
		
		
		@GetMapping("/getAllMovie")
		public ResponseEntity<?> getAllMovies() {
			List<Movie> movies = movieService.getAllMovies();

			if (movies == null || movies.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No movies found in the database");
			}

			return ResponseEntity.ok(movies);
		}
		
		
		
		 @GetMapping("/{id}")
		    public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable Long id) {
		        return ResponseEntity.ok(movieService.getMovieById(id));
		    }
		
		 
		
		 @PutMapping("/{id}")
		    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @Validated @RequestBody Movie movie) {
		        return ResponseEntity.ok(movieService.updateMovie(id, movie));
		    }
		
		 
		 @DeleteMapping("/{id}")
		    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
		        movieService.deleteMovie(id);
		        return ResponseEntity.ok("Movie deleted successfully");
		    }
				
}
