package com.kluczewski.filmservice.service;

import com.kluczewski.filmservice.model.entity.Category;
import com.kluczewski.filmservice.model.entity.Film;
import com.kluczewski.filmservice.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Category save(Category category) {
        if(categoryRepository.existsByName(category.getName())) {
            return categoryRepository.findByName(category.getName());
        } else {
            return categoryRepository.save(category);
        }
    }
}
