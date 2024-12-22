package com.example.daw1_proyecto_final.service;


import com.example.daw1_proyecto_final.dto.FilmCreateDto;
import com.example.daw1_proyecto_final.dto.FilmDeleteDto;
import com.example.daw1_proyecto_final.dto.FilmDetailDto;
import com.example.daw1_proyecto_final.dto.FilmDto;

import java.util.List;

public interface FilmService {
    List<FilmDto> findAllFilms();
    FilmDetailDto findDetailById(Integer id);
    Boolean updateFilm(FilmDetailDto filmDetailDto);
    FilmCreateDto createFilm(FilmCreateDto filmCreateDto);
    FilmDeleteDto deleteFilmById(Integer id);
}
