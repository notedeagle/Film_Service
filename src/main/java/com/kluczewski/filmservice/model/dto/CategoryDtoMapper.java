package com.kluczewski.filmservice.model.dto;

import com.kluczewski.filmservice.model.entity.Category;

import java.util.Set;
import java.util.stream.Collectors;

public class CategoryDtoMapper {

    private CategoryDtoMapper() {};

    public static Set<CategoryDto> mapToCategoryDtos(Set<Category> categories) {
        return categories.stream()
                .map(CategoryDtoMapper::mapToCategoryDto)
                .collect(Collectors.toSet());
    }

    private static CategoryDto mapToCategoryDto(Category category) {
        return CategoryDto.builder()
                .name(category.getName())
                .build();
    }
}
