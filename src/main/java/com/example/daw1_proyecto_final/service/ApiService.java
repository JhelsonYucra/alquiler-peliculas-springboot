package com.example.daw1_proyecto_final.service;

import com.example.daw1_proyecto_final.model.Film;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ApiService {

    public ResponseEntity<Map<String, Object>> listaFilm();
    public ResponseEntity<Map<String, Object>> listaFilmPorId(Integer id);
    public ResponseEntity<Map<String, Object>> agregaFilm(Film film);
    public ResponseEntity<Map<String, Object>> actualizaFilm(Film film, Integer id);
    public ResponseEntity<Map<String, Object>> eliminarFilm(Integer id);
}
