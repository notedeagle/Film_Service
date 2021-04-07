package com.kluczewski.filmservice.repository;

import com.kluczewski.filmservice.model.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

    Director findByLastnameAndFirstname(String lastname, String firstname);

    boolean existsByLastnameAndFirstname(String lastname, String firstname);
}
