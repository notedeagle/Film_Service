package com.kluczewski.filmservice.service;

import com.kluczewski.filmservice.model.dto.*;
import com.kluczewski.filmservice.model.entity.Director;
import com.kluczewski.filmservice.repository.DirectorRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class DirectorService {

    private final ModelMapper mapper;

    private final DirectorRepository directorRepository;

    @Transactional
    public DirectorDto findById(Long id) {
        Director director = mapper.map(directorRepository.findById(id).orElseThrow(), Director.class);
        return mapper.map(director, DirectorDto.class);
    }

    @Transactional
    public Director findDirectorById(Long id) {
        return mapper.map(directorRepository.findById(id).orElseThrow(), Director.class);
    }

    @Transactional
    public Director save(Director director) {
        if(directorRepository.existsByLastnameAndFirstname(director.getLastname(), director.getFirstname())) {
            return directorRepository.findByLastnameAndFirstname(director.getLastname(), director.getFirstname());
        } else {
            return directorRepository.save(director);
        }
    }

    @Transactional
    public DirectorInsertDto update(Long id, Director director) {
        Director directorToUpdate = findDirectorById(id);
        directorToUpdate.setFirstname(director.getFirstname());
        directorToUpdate.setLastname(director.getLastname());
        return mapper.map(directorToUpdate, DirectorInsertDto.class);
    }

    public List<Director> findAll(Sort.Direction sort) {
        return directorRepository.findAll(Sort.by(sort, "id"));
    }
}
