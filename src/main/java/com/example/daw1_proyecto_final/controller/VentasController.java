package com.example.daw1_proyecto_final.controller;


import com.example.daw1_proyecto_final.model.Ventas;
import com.example.daw1_proyecto_final.service.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
public class VentasController {

    @Autowired
    private VentasService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listar(){
        return service.listaVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> listarPorId(@PathVariable Long id){
        return service.listaVentasPorId(id);
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:4200")  // Permite solicitudes solo desde Angular
    public ResponseEntity<Map<String, Object>> agregar(@RequestBody Ventas ventas) {
        return service.agregaVentas(ventas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editar(@RequestBody Ventas ventas, @PathVariable Long id){
        return service.actualizaVentas(ventas, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id ) {
        return service.eliminarVentas(id);
    }


    @GetMapping("/enable")
    public ResponseEntity<Map<String, Object>> listarEstado(){
        return service.listaVentasEstado();
    }
}
