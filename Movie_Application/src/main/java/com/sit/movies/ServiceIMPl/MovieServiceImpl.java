package com.sit.movies.ServiceIMPl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sit.movies.Model.Movie;
import com.sit.movies.Repository.MovieRepository;
import com.sit.movies.Services.MovieService;

@Service
public class MovieServiceImpl  implements MovieService{
     
	@Autowired
	 private MovieRepository movieRepository;
	
	@Override
	public Movie createMovie(Movie movie) {
		
		return movieRepository.save(movie);
	}

	@Override
	public boolean existsByTitleAndYear(String title, int releaseYear) {
		return movieRepository.existsByTitleAndReleaseYear(title, releaseYear);
	}

	@Override 
	public List<Movie> getAllMovies() {
		
		return movieRepository.findAll();
	}

	 
}
