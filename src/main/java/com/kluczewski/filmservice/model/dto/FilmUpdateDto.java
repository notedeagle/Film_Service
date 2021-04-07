package com.kluczewski.filmservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmUpdateDto {

    private String title;
    private Double rating;
    private String description;
    private Integer releaseYear;
    private LocalDateTime updated;
}
