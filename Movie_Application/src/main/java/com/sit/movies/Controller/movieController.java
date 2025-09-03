package com.sit.movies.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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
	
		@PostMapping
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
}
