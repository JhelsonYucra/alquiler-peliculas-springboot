package com.example.daw1_proyecto_final.repository;

import com.example.daw1_proyecto_final.model.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentasRepository extends JpaRepository<Ventas, Long> {
    List<Ventas> findAllByEstado(String estado);
}
