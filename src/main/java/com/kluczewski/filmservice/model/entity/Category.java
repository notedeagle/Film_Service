package com.kluczewski.filmservice.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Film> films = new HashSet<>();

    public void addFilm(Film film) {
        films.add(film);
        film.addCategories(this);
    }
}
