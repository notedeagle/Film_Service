package com.kluczewski.filmservice.controller;

import com.kluczewski.filmservice.model.dto.DirectorDto;
import com.kluczewski.filmservice.model.dto.DirectorDtoMapper;
import com.kluczewski.filmservice.model.dto.FilmDto;
import com.kluczewski.filmservice.model.dto.FilmDtoMapper;
import com.kluczewski.filmservice.model.entity.Director;
import com.kluczewski.filmservice.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/directors")
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping("/{id}")
    public DirectorDto getDirector(@PathVariable Long id) {
        return directorService.findById(id);
    }

    @GetMapping
    public List<DirectorDto> getAll(Sort.Direction sort) {
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return DirectorDtoMapper.mapToDirectorDtos(directorService.findAll(sortDirection));
    }
}
