package com.example.daw1_proyecto_final.serviceImplement;

import com.example.daw1_proyecto_final.dto.ApiDto;
import com.example.daw1_proyecto_final.model.Film;
import com.example.daw1_proyecto_final.repository.ApiRepository;
import com.example.daw1_proyecto_final.repository.FilmRepository;
import com.example.daw1_proyecto_final.repository.LanguageRepository;
import com.example.daw1_proyecto_final.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ApiServiceImplm implements ApiService {

    @Autowired
    ApiRepository apiRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public ResponseEntity<Map<String, Object>> listaFilm() {
        List<ApiDto> films = new ArrayList<>();
        Iterable<Film> iterable = filmRepository.findAll();

        iterable.forEach(film -> {
            ApiDto apiDto = new ApiDto(
                    film.getFilmId(),
                    film.getTitle(),
                    film.getDescription(), // Obtienes la descripción del film
                    film.getReleaseYear(), // Obtienes el año de estreno
                    film.getLanguage() != null ? film.getLanguage().getName() : "Unknown", // Asegúrate de que no sea null
                    film.getRentalDuration(), // Duración del alquiler
                    film.getRentalRate(), // Tasa de alquiler
                    film.getLength(), // Longitud de la película
                    film.getReplacementCost(), // Costo de reemplazo
                    film.getRating(), // Calificación
                    film.getSpecialFeatures(), // Características especiales
                    film.getLastUpdate() // Última actualización
            );
            films.add(apiDto);
        });

        Map<String, Object> response = new HashMap<>();
        response.put("fecha", LocalDateTime.now().toString());
        response.put("estado", "OK");
        response.put("titulo", "Lista de Peliculas");
        response.put("Peliculas", films);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> listaFilmPorId(Integer id) {
        Optional<Film> filmOptional = filmRepository.findById(id);

        if (filmOptional.isPresent()) {
            Film film = filmOptional.get();
            ApiDto apiDto = new ApiDto(
                    film.getFilmId(),
                    film.getTitle(),
                    film.getDescription(),
                    film.getReleaseYear(),
                    film.getLanguage() != null ? film.getLanguage().getName() : "Unknown",
                    film.getRentalDuration(),
                    film.getRentalRate(),
                    film.getLength(),
                    film.getReplacementCost(),
                    film.getRating(),
                    film.getSpecialFeatures(),
                    film.getLastUpdate()
            );

            Map<String, Object> response = new HashMap<>();
            response.put("fecha", LocalDateTime.now().toString());
            response.put("estado", "OK");
            response.put("titulo", "Detalle de Película");
            response.put("Pelicula", apiDto);

            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("fecha", LocalDateTime.now().toString());
            response.put("estado", "ERROR");
            response.put("titulo", "Película no encontrada");
            response.put("mensaje", "No se encontró una película con el ID: " + id);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> agregaFilm(Film film) {
        Map<String, Object> respuesta = new HashMap<>();
        apiRepository.save(film);
        respuesta.put("mensaje", "Se añadio correctamente el film");
        respuesta.put("fecha", new Date());
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("productos", film);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }


    @Override
    public ResponseEntity<Map<String, Object>> actualizaFilm(Film film, Integer id) {
        Optional<Film> filmOptional = filmRepository.findById(id);

        if (filmOptional.isPresent()) {
            Film existingFilm = filmOptional.get();
            // Aquí puedes actualizar solo los campos que deseas actualizar
            existingFilm.setTitle(film.getTitle());
            existingFilm.setDescription(film.getDescription());
            existingFilm.setReleaseYear(film.getReleaseYear());
            existingFilm.setLanguage(film.getLanguage());
            existingFilm.setRentalDuration(film.getRentalDuration());
            existingFilm.setRentalRate(film.getRentalRate());
            existingFilm.setLength(film.getLength());
            existingFilm.setReplacementCost(film.getReplacementCost());
            existingFilm.setRating(film.getRating());
            existingFilm.setSpecialFeatures(film.getSpecialFeatures());
            existingFilm.setLastUpdate(new Date()); // Actualiza la fecha de la última modificación

            Film updatedFilm = filmRepository.save(existingFilm);

            ApiDto apiDto = new ApiDto(
                    updatedFilm.getFilmId(),
                    updatedFilm.getTitle(),
                    updatedFilm.getDescription(),
                    updatedFilm.getReleaseYear(),
                    updatedFilm.getLanguage() != null ? updatedFilm.getLanguage().getName() : "Unknown",
                    updatedFilm.getRentalDuration(),
                    updatedFilm.getRentalRate(),
                    updatedFilm.getLength(),
                    updatedFilm.getReplacementCost(),
                    updatedFilm.getRating(),
                    updatedFilm.getSpecialFeatures(),
                    updatedFilm.getLastUpdate()
            );

            Map<String, Object> response = new HashMap<>();
            response.put("fecha", LocalDateTime.now().toString());
            response.put("estado", "OK");
            response.put("titulo", "Película Actualizada");
            response.put("Pelicula", apiDto);

            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("fecha", LocalDateTime.now().toString());
            response.put("estado", "ERROR");
            response.put("titulo", "Película no encontrada");
            response.put("mensaje", "No se encontró una película con el ID: " + id);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @Override
    public ResponseEntity<Map<String, Object>> eliminarFilm(Integer id) {
        try {
            if (filmRepository.existsById(id)) {
                filmRepository.deleteById(id);

                Map<String, Object> response = new HashMap<>();
                response.put("fecha", LocalDateTime.now().toString());
                response.put("estado", "OK");
                response.put("titulo", "Película Eliminada");
                response.put("mensaje", "Película con ID " + id + " eliminada correctamente.");

                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("fecha", LocalDateTime.now().toString());
                response.put("estado", "ERROR");
                response.put("titulo", "Película no encontrada");
                response.put("mensaje", "No se encontró una película con el ID: " + id);

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("fecha", LocalDateTime.now().toString());
            response.put("estado", "ERROR");
            response.put("titulo", "Error al eliminar película");
            response.put("mensaje", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
