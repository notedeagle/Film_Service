package com.kluczewski.filmservice.repository;

import com.kluczewski.filmservice.model.entity.Film;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("SELECT p FROM Film p INNER JOIN p.categories INNER JOIN p.directors")
    List<Film> findAllFilms(Pageable page);
}
