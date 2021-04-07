package com.kluczewski.filmservice.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Double rating;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "film_directors",
            joinColumns = {@JoinColumn(name = "film_id",
                    insertable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "director_id",
                    insertable = false, updatable = false)})
    private Set<Director> directors = new HashSet<>();

    private String description;
    private Integer releaseYear;

    @ManyToMany
    @JoinTable(name = "film_categories",
    joinColumns = {@JoinColumn(name = "film_id",
    insertable = false, updatable = false)},
    inverseJoinColumns = {@JoinColumn(name = "category_name",
    insertable = false, updatable = false)})
    private Set<Category> categories = new HashSet<>();

    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime updated = LocalDateTime.now();

    public void addCategories(Category category) {
        categories.add(category);
    }
}
