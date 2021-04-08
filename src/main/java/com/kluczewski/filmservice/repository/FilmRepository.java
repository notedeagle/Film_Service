package com.kluczewski.filmservice.repository;

import com.kluczewski.filmservice.model.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
}
