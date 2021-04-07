package com.kluczewski.filmservice.model.dto;

import com.kluczewski.filmservice.model.entity.Director;

import java.util.Set;
import java.util.stream.Collectors;

public class DirectorDtoMapper {

    private DirectorDtoMapper() {};

    public static Set<DirectorDto> mapToDirectorDtos(Set<Director> directors) {
        return directors.stream()
                .map(DirectorDtoMapper::mapToDirectorDto)
                .collect(Collectors.toSet());
    }

    private static DirectorDto mapToDirectorDto(Director director) {
        return DirectorDto.builder()
                .firstname(director.getFirstname())
                .lastname(director.getLastname())
                .build();
    }
}
