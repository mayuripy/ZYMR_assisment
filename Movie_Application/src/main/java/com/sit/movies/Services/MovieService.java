package com.sit.movies.Services;

import java.util.List;
import java.util.Optional;

import com.sit.movies.Model.Movie;

public interface MovieService {
    
	public boolean existsByTitleAndYear(String title, int releaseYear);
	 
	 public Movie createMovie(Movie movie);
	 
	 List<Movie> getAllMovies();
	 
	 Optional<Movie> getMovieById(Long id);
	 
	 Movie updateMovie(Long id, Movie movie);
	 
}
