package com.example.daw1_proyecto_final.service;

import com.example.daw1_proyecto_final.model.Ventas;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface VentasService {
    public ResponseEntity<Map<String, Object>> listaVentas();
    public ResponseEntity<Map<String, Object>> listaVentasPorId(Long id);
    public ResponseEntity<Map<String, Object>> agregaVentas(Ventas ventas);
    public ResponseEntity<Map<String, Object>> actualizaVentas(Ventas ventas, Long id);
    public ResponseEntity<Map<String, Object>> eliminarVentas(Long id);
    public ResponseEntity<Map<String, Object>> listaVentasEstado();
}
