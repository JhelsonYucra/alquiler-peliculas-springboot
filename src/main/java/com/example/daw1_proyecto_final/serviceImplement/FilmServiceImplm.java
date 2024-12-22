package com.example.daw1_proyecto_final.serviceImplement;

import com.example.daw1_proyecto_final.dto.FilmCreateDto;
import com.example.daw1_proyecto_final.dto.FilmDeleteDto;
import com.example.daw1_proyecto_final.dto.FilmDetailDto;
import com.example.daw1_proyecto_final.dto.FilmDto;
import com.example.daw1_proyecto_final.model.Film;
import com.example.daw1_proyecto_final.model.Language;
import com.example.daw1_proyecto_final.repository.FilmRepository;
import com.example.daw1_proyecto_final.repository.LanguageRepository;
import com.example.daw1_proyecto_final.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImplm implements FilmService {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    LanguageRepository languageRepository;

    /* CRUD INTERNO*/
    @Override
    public List<FilmDto> findAllFilms() {

        List<FilmDto> films = new ArrayList<FilmDto>();
        Iterable<Film> iterable = filmRepository.findAll();
        iterable.forEach(film -> {
            FilmDto filmDto = new FilmDto(film.getFilmId(),
                    film.getTitle(),
                    film.getLanguage().getName(),
                    film.getRentalDuration(),
                    film.getRentalRate());
            films.add(filmDto);
        });
        return films;

    }

    @Override
    public FilmDetailDto findDetailById(Integer id) {

        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(
                film -> new FilmDetailDto(film.getFilmId(),
                        film.getTitle(),
                        film.getDescription(),
                        film.getReleaseYear(),
                        film.getRentalDuration(),
                        film.getRentalRate(),
                        film.getLength(),
                        film.getReplacementCost(),
                        film.getRating(),
                        film.getSpecialFeatures(),
                        film.getLastUpdate())
        ).orElse(null);

    }

    @Override
    public Boolean updateFilm(FilmDetailDto filmDetailDto) {
        Optional<Film> optional = filmRepository.findById(filmDetailDto.filmId());
        return optional.map(
                film -> {
                    film.setTitle(filmDetailDto.title());
                    film.setDescription(filmDetailDto.description());
                    film.setReleaseYear(filmDetailDto.releaseYear());
                    film.setRentalDuration(filmDetailDto.rentalDuration());
                    film.setRentalRate(filmDetailDto.rentalRate());
                    film.setLength(filmDetailDto.length());
                    film.setReplacementCost(filmDetailDto.replacementCost());
                    film.setRating(filmDetailDto.rating());
                    film.setSpecialFeatures(filmDetailDto.specialFeatures());
                    film.setLastUpdate(new Date());
                    filmRepository.save(film);
                    return true;
                }
        ).orElse(false);

    }

    @Override
    public FilmCreateDto createFilm(FilmCreateDto filmCreateDto) {
        // Crear una nueva entidad Film
        Film film = new Film();

        // Asignar los valores del DTO a la entidad
        film.setTitle(filmCreateDto.title());
        film.setDescription(filmCreateDto.description());
        film.setReleaseYear(filmCreateDto.releaseYear());
        film.setRentalDuration(filmCreateDto.rentalDuration());
        film.setRentalRate(filmCreateDto.rentalRate());
        film.setLength(filmCreateDto.length());
        film.setReplacementCost(filmCreateDto.replacementCost());
        film.setRating(filmCreateDto.rating());
        film.setSpecialFeatures(filmCreateDto.specialFeatures());
        film.setLastUpdate(new Date());

        // Relación con el idioma
        if (filmCreateDto.originalLanguageId() != null) {
            Language language = languageRepository.findById(filmCreateDto.originalLanguageId())
                    .orElseThrow(() -> new IllegalArgumentException("El idioma no existe con el ID: " + filmCreateDto.originalLanguageId()));
            film.setLanguage(language);
        }

        // Guardar la entidad Film en la base de datos
        Film savedFilm = filmRepository.save(film);

        // Crear y devolver el FilmCreateDto actualizado
        return new FilmCreateDto(
                savedFilm.getFilmId(),
                savedFilm.getTitle(),
                savedFilm.getDescription(),
                savedFilm.getReleaseYear(),
                savedFilm.getLanguage() != null ? savedFilm.getLanguage().getLanguageId() : null,
                savedFilm.getRentalDuration(),
                savedFilm.getRentalRate(),
                savedFilm.getLength(),
                savedFilm.getReplacementCost(),
                savedFilm.getRating(),
                savedFilm.getSpecialFeatures(),
                savedFilm.getLastUpdate()
        );
    }

    @Override
    public FilmDeleteDto deleteFilmById(Integer id) {
        // Buscar la película por ID
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El ID de la película no es válido."));
        // Eliminar la película y sus relaciones en cascada (gracias a las configuraciones anteriores)
        filmRepository.deleteById(id);
        // Crear y devolver el DTO con el ID de la película eliminada
        return new FilmDeleteDto(film.getFilmId());
    }

}

