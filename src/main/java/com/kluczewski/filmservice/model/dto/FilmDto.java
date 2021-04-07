package com.kluczewski.filmservice.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto {

    private Long id;
    private String title;
    private Double rating;
    private Set<DirectorDto> directors;
    private String description;
    private Integer releaseYear;
    private Set<CategoryDto> categories;
    private LocalDateTime created;
    private LocalDateTime updated;
}
