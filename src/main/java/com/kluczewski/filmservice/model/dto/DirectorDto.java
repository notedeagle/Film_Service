package com.kluczewski.filmservice.model.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectorDto {

    private Long id;
    private String firstname;
    private String lastname;
}
