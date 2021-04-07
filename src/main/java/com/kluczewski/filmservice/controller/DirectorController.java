package com.kluczewski.filmservice.controller;

import com.kluczewski.filmservice.model.dto.DirectorDto;
import com.kluczewski.filmservice.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/directors")
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping("/{id}")
    public DirectorDto getDirector(@PathVariable Long id) {
        return directorService.findById(id);
    }
}
