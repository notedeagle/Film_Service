package com.kluczewski.filmservice.model.dto;

import com.kluczewski.filmservice.model.entity.Director;
import com.kluczewski.filmservice.model.entity.Film;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FilmDtoMapper {

    private FilmDtoMapper() {};

    public static List<FilmWithDirectorDto> mapToFilmWithDirectorDtos(List<Film> films) {
        return films.stream()
                .map(FilmDtoMapper::mapToFilmWithDirectorDto)
                .collect(Collectors.toList());
    }

    public static Set<FilmWithDirectorDto> mapToFilmWithDirectorDtos(Set<Film> films) {
        return films.stream()
                .map(FilmDtoMapper::mapToFilmWithDirectorDto)
                .collect(Collectors.toSet());
    }

    public static Set<FilmDto> mapToFilmDto(Set<Film> films) {
        return films.stream()
                .map(FilmDtoMapper::mapToFilmDto)
                .collect(Collectors.toSet());
    }

    private static FilmWithDirectorDto mapToFilmWithDirectorDto(Film film) {
        return FilmWithDirectorDto.builder()
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

    private static FilmDto mapToFilmDto(Film film) {
        return FilmDto.builder()
                .id(film.getId())
                .title(film.getTitle())
                .rating(film.getRating())
                .description(film.getDescription())
                .releaseYear(film.getReleaseYear())
                .created(film.getCreated())
                .updated(film.getUpdated())
                .categories(CategoryDtoMapper.mapToCategoryDtos(film.getCategories()))
                .build();
    }
}
