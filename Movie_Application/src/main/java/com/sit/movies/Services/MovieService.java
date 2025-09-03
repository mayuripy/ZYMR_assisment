package com.sit.movies.Services;

import com.sit.movies.Model.Movie;

public interface MovieService {
    
	public boolean existsByTitleAndYear(String title, int releaseYear);
	 
	 public Movie createMovie(Movie movie);
	 
}
