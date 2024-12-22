package com.example.daw1_proyecto_final.dto;

import java.util.Date;

public record FilmCreateDto(
                            Integer filmId,
                            String title,
                            String description,
                            Integer releaseYear,
                            Integer originalLanguageId,
                            Integer rentalDuration,
                            Double rentalRate,
                            Integer length,
                            Double replacementCost,
                            String rating,
                            String specialFeatures,
                            Date lastUpdate) {
}