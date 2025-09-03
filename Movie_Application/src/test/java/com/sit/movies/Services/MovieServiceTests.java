package com.sit.movies.Services;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sit.movies.Model.Movie;
import com.sit.movies.Services.MovieService;

@DataJpaTest
class MovieServiceTests {

    @Autowired
    private MovieService movieService;

    private Movie movie;

    @BeforeEach
    void setUp() {
        movieService.getAllMovies().forEach(m -> movieService.deleteMovie(m.getId()));

        movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setDirector("Christopher Nolan");
        movie.setReleaseYear(2014);
        movie.setGenre("Sci-Fi");
        movie.setRating(9);
    }

    @Test
    void testCreateMovie() {
        Movie saved = movieService.createMovie(movie);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("Interstellar");
    }

    @Test
    void testGetAllMovies() {
        movieService.createMovie(movie);
        List<Movie> movies = movieService.getAllMovies();
        assertThat(movies.size()).isEqualTo(1);
    }

    @Test
    void testGetMovieById() {
        Movie saved = movieService.createMovie(movie);
        Optional<Movie> found = movieService.getMovieById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Interstellar");
    }

    @Test
    void testUpdateMovie() {
        Movie saved = movieService.createMovie(movie);
        saved.setRating(10);
        Movie updated = movieService.updateMovie(saved.getId(), saved);
        assertThat(updated.getRating()).isEqualTo(10);
    }

    @Test
    void testDeleteMovie() {
        Movie saved = movieService.createMovie(movie);
        movieService.deleteMovie(saved.getId());
        Optional<Movie> deleted = movieService.getMovieById(saved.getId());
        assertThat(deleted).isEmpty();
    }
}
