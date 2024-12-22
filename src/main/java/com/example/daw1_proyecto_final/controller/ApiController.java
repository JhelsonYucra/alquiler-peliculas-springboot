package com.example.daw1_proyecto_final.controller;

import com.example.daw1_proyecto_final.model.Film;
import com.example.daw1_proyecto_final.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/films")
@RestController
public class ApiController {

    @Autowired
    ApiService apiService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listar(){
        return apiService.listaFilm();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> listarPorId(@PathVariable Integer id){
        return apiService.listaFilmPorId(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregar(@RequestBody Film film) {
        return apiService.agregaFilm(film);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editar(@RequestBody Film film, @PathVariable Integer id){
        return apiService.actualizaFilm(film, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Integer id ) {
        return apiService.eliminarFilm(id);
    }

}
