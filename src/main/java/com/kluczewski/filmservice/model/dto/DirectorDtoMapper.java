package com.kluczewski.filmservice.model.dto;

import com.kluczewski.filmservice.model.entity.Director;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DirectorDtoMapper {

    private DirectorDtoMapper() {};

    public static Set<DirectorDto> mapToDirectorDtos(Set<Director> directors) {
        return directors.stream()
                .map(DirectorDtoMapper::mapToDirectorDto)
                .collect(Collectors.toSet());
    }

    public static List<DirectorDto> mapToDirectorDtos(List<Director> directors) {
        return directors.stream()
                .map(DirectorDtoMapper::mapToDirectorDto)
                .collect(Collectors.toList());
    }

    private static DirectorDto mapToDirectorDto(Director director) {
        return DirectorDto.builder()
                .id(director.getId())
                .firstname(director.getFirstname())
                .lastname(director.getLastname())
                .build();
    }
}
