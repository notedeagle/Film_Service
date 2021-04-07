package com.kluczewski.filmservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmUpdateDto {

    private String title;
    private Double rating;
    private String description;
    private Integer releaseYear;
    private Set<CategoryInsertDto> categories;
    private Set<DirectorInsertDto> directors;
    private LocalDateTime updated;
}
