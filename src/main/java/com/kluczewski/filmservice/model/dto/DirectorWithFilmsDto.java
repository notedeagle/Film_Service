package com.kluczewski.filmservice.model.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectorWithFilmsDto {

    private Long id;
    private String firstname;
    private String lastname;
    private Set<FilmDto> films;
}
