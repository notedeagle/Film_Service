package com.kluczewski.filmservice.service;

import com.kluczewski.filmservice.model.dto.FilmDto;
import com.kluczewski.filmservice.model.entity.Category;
import com.kluczewski.filmservice.model.entity.Director;
import com.kluczewski.filmservice.model.entity.Film;
import com.kluczewski.filmservice.repository.FilmRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final CategoryService categoryService;
    private final DirectorService directorService;
    private final ModelMapper mapper;

    private static final int PAGE_SIZE = 20;

    @Transactional
    public Film save(Film film) {
        Set<Category> categories = film.getCategories().stream()
                .map(categoryService::save)
                .collect(Collectors.toSet());
        categories.forEach(category -> category.addFilm(film));
        film.setCategories(categories);
        Set<Director> directors = film.getDirectors().stream()
                .map(directorService::save)
                .collect(Collectors.toSet());
        film.setDirectors(directors);
        return filmRepository.save(film);
    }

    @Transactional
    public FilmDto findById(Long id) {
        Film film = mapper.map(filmRepository.findById(id).orElseThrow(), Film.class);
        return mapper.map(film, FilmDto.class);
    }

    @Transactional
    public Film findFilmById(Long id) {
        return filmRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void deleteById(Long id) {
        if(filmRepository.existsById(id)) {
            filmRepository.deleteById(id);
        }
    }

    @Transactional
    public Film update(Long id, Film film) {
        Film filmToUpdate = findFilmById(id);
        filmToUpdate.setTitle(film.getTitle());
        filmToUpdate.setRating(film.getRating());
        filmToUpdate.setReleaseYear(film.getReleaseYear());
        filmToUpdate.setDescription(film.getDescription());
        filmToUpdate.setUpdated(LocalDateTime.now());
        return filmToUpdate;
    }

    public List<Film> findAll(Sort.Direction sort) {
        return filmRepository.findAll(Sort.by(sort, "id"));
    }
}
