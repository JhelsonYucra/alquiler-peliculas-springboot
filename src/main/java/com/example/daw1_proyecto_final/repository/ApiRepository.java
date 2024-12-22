package com.example.daw1_proyecto_final.repository;

import com.example.daw1_proyecto_final.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<Film, Integer> {

}
