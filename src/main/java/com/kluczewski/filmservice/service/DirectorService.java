package com.kluczewski.filmservice.service;

import com.kluczewski.filmservice.model.dto.DirectorDto;
import com.kluczewski.filmservice.model.entity.Director;
import com.kluczewski.filmservice.repository.DirectorRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@AllArgsConstructor
public class DirectorService {

    private final ModelMapper mapper;

    private final DirectorRepository directorRepository;

    public DirectorDto findById(Long id) {
        Director director = mapper.map(directorRepository.findById(id).orElseThrow(), Director.class);
        return mapper.map(director, DirectorDto.class);
    }

    @Transactional
    public Director save(Director director) {
        if(directorRepository.existsByLastnameAndFirstname(director.getLastname(), director.getFirstname())) {
            return directorRepository.findByLastnameAndFirstname(director.getLastname(), director.getFirstname());
        } else {
            return directorRepository.save(director);
        }
    }
}
