package com.sit.movies.ServiceIMPl;

import java.util.List;
import java.util.Optional;

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
	

	@Service
	public class MovieService {

	    @Autowired
	    private MovieRepository movieRepository;

	    public Optional<Movie> getMovieById(Long id) {
	    	
	        return movieRepository.findById(id);
	    }
	}


	@Override
	public Optional<Movie> getMovieById(Long id) {
		
		return movieRepository.findById(id);
	}

	@Override
	public Movie updateMovie(Long id, Movie movie) {

		if (id == null || id <= 0) {
			System.out.println("Invalid ID: " + id);
			return null; // or return a default Movie object
		}

		if (movie == null || movie.getTitle() == null || movie.getTitle().trim().isEmpty() || movie.getGenre() == null
				|| movie.getGenre().trim().isEmpty() || movie.getReleaseYear() < 1888) {
			System.out.println("Invalid movie details");
			return null; // or return a default Movie object
		}

		Optional<Movie> optionalMovie = movieRepository.findById(id);
		if (!optionalMovie.isPresent()) {
			System.out.println("Movie not found with ID: " + id);
			return null; // or return a default Movie object
		}

		Movie existing = optionalMovie.get();

		existing.setTitle(movie.getTitle());
		existing.setDirector(movie.getDirector());
		existing.setReleaseYear(movie.getReleaseYear());
		existing.setGenre(movie.getGenre());

		// 5. Save and return updated entity
		return movieRepository.save(existing);
	}
	
	
	
	@Override
	public void deleteMovie(Long id) {
	    Optional<Movie> movie = getMovieById(id);

	    if (movie.isPresent()) {
	        movieRepository.delete(movie.get());
	        System.out.println("Movie deleted successfully.");
	    } else {
	        System.out.println("Movie not found with ID: " + id);
	        
	    }
	}

	 
}
