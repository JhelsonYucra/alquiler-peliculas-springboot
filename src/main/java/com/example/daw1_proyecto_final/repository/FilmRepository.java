package com.example.daw1_proyecto_final.repository;

import com.example.daw1_proyecto_final.model.Film;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends CrudRepository<Film, Integer> {

    @Cacheable(value = "films")
    Iterable<Film> findAll();

    @CacheEvict(value = "films", allEntries = true) // Finciona como crear también
    Film save(Film film);

    @CacheEvict(value = "films", allEntries = true)
    void deleteById(Integer id);
}
