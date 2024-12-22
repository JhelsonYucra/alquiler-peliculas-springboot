package com.example.daw1_proyecto_final.dto;

public record FilmDto(Integer filmId,
                      String title,
                      String language,
                      Integer rentalDuration,
                      Double rentalRate) {
}