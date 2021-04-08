package com.kluczewski.filmservice.controller;

import com.kluczewski.filmservice.model.dto.FilmDto;
import com.kluczewski.filmservice.model.dto.FilmDtoMapper;
import com.kluczewski.filmservice.model.dto.FilmInsertDto;
import com.kluczewski.filmservice.model.dto.FilmUpdateDto;
import com.kluczewski.filmservice.model.entity.Film;
import com.kluczewski.filmservice.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;
    private final ModelMapper mapper;

    @PostMapping
    public FilmDto addFilm(@RequestBody FilmInsertDto filmInsertDto) {
        Film film = mapper.map(filmInsertDto, Film.class);
        Film filmCreated = filmService.save(film);
        return mapper.map(filmCreated, FilmDto.class);
    }

    @GetMapping("{id}")
    public FilmDto getFilm(@PathVariable Long id) {
        return filmService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable Long id) {
        filmService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Film updateFilm(@PathVariable Long id, @RequestBody FilmUpdateDto filmUpdateDto) {
        Film filmUpdated = filmService.update(id, mapper.map(filmUpdateDto, Film.class));
        return filmService.update(id, mapper.map(filmUpdated, Film.class));
    }

    @GetMapping
    public List<FilmDto> findAll(Sort.Direction sort) {
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return FilmDtoMapper.mapToFilmDtos(filmService.findAll(sortDirection));
    }
}
