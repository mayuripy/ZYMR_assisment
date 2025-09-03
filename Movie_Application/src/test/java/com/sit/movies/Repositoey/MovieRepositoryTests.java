package com.sit.movies.Repositoey;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sit.movies.Model.Movie;
import com.sit.movies.Repository.MovieRepository;

@DataJpaTest
class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void testSaveMovie() {
        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setDirector("Christopher Nolan");
        movie.setReleaseYear(2010);
        movie.setGenre("Sci-Fi");
        movie.setRating(9);

        Movie saved = movieRepository.save(movie);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("Inception");
    }

    @Test
    void testExistsByTitleAndReleaseYear() {
        Movie movie = new Movie();
        movie.setTitle("Tenet");
        movie.setDirector("Christopher Nolan");
        movie.setReleaseYear(2020);
        movie.setGenre("Action");
        movie.setRating(8);

        movieRepository.save(movie);

        boolean exists = movieRepository.existsByTitleAndReleaseYear("Tenet", 2020);
        assertThat(exists).isTrue();
    }

    @Test
    void testFindAllMovies() {
        Movie movie1 = new Movie();
        movie1.setTitle("Movie1");
        movie1.setDirector("Director1");
        movie1.setReleaseYear(2000);
        movie1.setGenre("Genre1");
        movie1.setRating(7);

        Movie movie2 = new Movie();
        movie2.setTitle("Movie2");
        movie2.setDirector("Director2");
        movie2.setReleaseYear(2005);
        movie2.setGenre("Genre2");
        movie2.setRating(8);

        movieRepository.save(movie1);
        movieRepository.save(movie2);

        List<Movie> movies = movieRepository.findAll();
        assertThat(movies.size()).isEqualTo(2);
    }
}
