package com.kluczewski.filmservice.model.dto;

import com.kluczewski.filmservice.model.entity.Film;

import java.util.List;
import java.util.stream.Collectors;

public class FilmDtoMapper {

    private FilmDtoMapper() {};

    public static List<FilmDto> mapToFilmDtos(List<Film> films) {
        return films.stream()
                .map(FilmDtoMapper::mapToFilmDto)
                .collect(Collectors.toList());
    }

    private static FilmDto mapToFilmDto(Film film) {
        return FilmDto.builder()
                .id(film.getId())
                .title(film.getTitle())
                .rating(film.getRating())
                .description(film.getDescription())
                .releaseYear(film.getReleaseYear())
                .created(film.getCreated())
                .updated(film.getUpdated())
                .directors(DirectorDtoMapper.mapToDirectorDtos(film.getDirectors()))
                .categories(CategoryDtoMapper.mapToCategoryDtos(film.getCategories()))
                .build();
    }
}
