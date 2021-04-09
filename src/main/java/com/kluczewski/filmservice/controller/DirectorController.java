package com.kluczewski.filmservice.controller;

import com.kluczewski.filmservice.model.dto.*;
import com.kluczewski.filmservice.model.entity.Director;
import com.kluczewski.filmservice.model.entity.Film;
import com.kluczewski.filmservice.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/directors")
public class DirectorController {

    private final DirectorService directorService;
    private final ModelMapper mapper;

    @GetMapping("/{id}")
    public DirectorDto getDirector(@PathVariable Long id) {
        return directorService.findById(id);
    }

    @GetMapping
    public List<DirectorDto> getAll(Sort.Direction sort) {
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return DirectorDtoMapper.mapToDirectorDtos(directorService.findAll(sortDirection));
    }

    @GetMapping("/films")
    public List<DirectorWithFilmsDto> getAllDirectorsWithFilms(Sort.Direction sort) {
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return DirectorDtoMapper.mapToDirectorWithFilmsDtos(directorService.findAll(sortDirection));
    }

    @PutMapping("/{id}")
    public DirectorInsertDto updateDirector(@PathVariable Long id, @RequestBody DirectorInsertDto directorDto) {
        DirectorInsertDto directorUpdate = directorService.update(id, mapper.map(directorDto, Director.class));
        return directorService.update(id, mapper.map(directorUpdate, Director.class));
    }
}
