package com.example.daw1_proyecto_final.controller;


import com.example.daw1_proyecto_final.dto.FilmCreateDto;
import com.example.daw1_proyecto_final.dto.FilmDeleteDto;
import com.example.daw1_proyecto_final.dto.FilmDetailDto;
import com.example.daw1_proyecto_final.dto.FilmDto;
import com.example.daw1_proyecto_final.model.Language;
import com.example.daw1_proyecto_final.repository.LanguageRepository;
import com.example.daw1_proyecto_final.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;


@RequestMapping("/maintenance")
@Controller
public class FilmController {

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    FilmService filmService;

    /*CRUD LOCAL*/

    @GetMapping("/start")
    public String start(Model model) {
        List<FilmDto> films = filmService.findAllFilms();
        model.addAttribute("films", films);
        return "maintenance";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        FilmDetailDto filmDetailDto = filmService.findDetailById(id);
        model.addAttribute("film", filmDetailDto);
        return "maintenance-detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        FilmDetailDto filmDetailDto = filmService.findDetailById(id);
        model.addAttribute("film", filmDetailDto);
        return "maintenance-edit";
    }

    @PostMapping("/edit-confirm")
    public String edit(@ModelAttribute FilmDetailDto film, Model model) {
        filmService.updateFilm(film);
        return "redirect:/maintenance/start";
    }

    @GetMapping("/create")
    public String create(Model model) {
        // Obtener la lista de idiomas desde el repositorio
        List<Language> languages = languageRepository.findAll();
        // Crear el DTO vacío para el formulario
        FilmCreateDto newFilm = new FilmCreateDto(null, "", "", null, null, null, null, null, null, null, null, null);
        // Pasar la lista de idiomas y el DTO al modelo
        model.addAttribute("film", newFilm);
        model.addAttribute("languages", languages); // Lista de idiomas
        return "maintenance-create"; // Nombre de la vista
    }

    @PostMapping("/create-confirm")
    public String create(@ModelAttribute FilmCreateDto film, Model model) {
        // Llamar al servicio para crear la película
        filmService.createFilm(film);
        // Redirigir a la página de inicio después de guardar
        return "redirect:/maintenance/start";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            // Llamamos al servicio para eliminar la película
            FilmDeleteDto filmDeleteDto = filmService.deleteFilmById(id);
            // Mensaje de éxito
            redirectAttributes.addFlashAttribute("message", "Película eliminada correctamente. ID: " + filmDeleteDto.filmId());
        } catch (Exception e) {
            // Mensaje de error
            redirectAttributes.addFlashAttribute("message", "Error al eliminar la película: " + e.getMessage());
        }
        // Redirigir a la lista de mantenimiento de películas
        return "redirect:/maintenance/start";
    }

}
