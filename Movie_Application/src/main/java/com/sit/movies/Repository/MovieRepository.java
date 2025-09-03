package com.sit.movies.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sit.movies.Model.Movie;

public interface MovieRepository  extends JpaRepository<Movie, Long>{

	 
}
