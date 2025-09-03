package com.sit.movies.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sit.movies.Model.Movie;
import com.sit.movies.Services.MovieService;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ObjectMapper objectMapper;

    private Movie movie;

    @BeforeEach
    void setUp() {
        movieService.getAllMovies().forEach(m -> movieService.deleteMovie(m.getId()));

        movie = new Movie();
        movie.setTitle("Tenet");
        movie.setDirector("Christopher Nolan");
        movie.setReleaseYear(2020);
        movie.setGenre("Action");
        movie.setRating(9);
    }

    @Test
    void testCreateMovie() throws Exception {
        mockMvc.perform(post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Tenet"));
    }

    @Test
    void testGetAllMovies() throws Exception {
        movieService.createMovie(movie);
        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetMovieById() throws Exception {
        Movie saved = movieService.createMovie(movie);
        mockMvc.perform(get("/api/movies/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Tenet"));
    }

    @Test
    void testUpdateMovie() throws Exception {
        Movie saved = movieService.createMovie(movie);
        saved.setRating(10);

        mockMvc.perform(put("/api/movies/" + saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saved)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating").value(10));
    }

    @Test
    void testDeleteMovie() throws Exception {
        Movie saved = movieService.createMovie(movie);
        mockMvc.perform(delete("/api/movies/" + saved.getId()))
                .andExpect(status().isOk());
    }
}
