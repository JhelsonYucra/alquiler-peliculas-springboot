package com.example.daw1_proyecto_final.serviceImplement;

import com.example.daw1_proyecto_final.model.Ventas;
import com.example.daw1_proyecto_final.repository.VentasRepository;
import com.example.daw1_proyecto_final.service.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VentasServiceImplm implements VentasService {

    @Autowired
    private VentasRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listaVentas() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Ventas> ventas = dao.findAll();
        if( !ventas.isEmpty() ) {
            respuesta.put("mensaje", "Lista de ventas");
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("ventas", ventas);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }else {
            respuesta.put("mensaje", "No existen registros");
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> listaVentasPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Ventas> ventas = dao.findById(id);
        if( ventas.isPresent() ) {
            respuesta.put("mensaje", "Busqueda correcta");
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("ventas", ventas);
            return ResponseEntity.ok().body(respuesta);
        }else {
            respuesta.put("mensaje", "Sin registros para el ID: " + id);
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> agregaVentas(Ventas ventas) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(ventas);
        respuesta.put("mensaje", "Se añadio correctamente la boleta");
        respuesta.put("fecha", new Date());
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("ventas", ventas);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> actualizaVentas(Ventas ventas, Long id) {
        Map<String,Object> respuesta = new HashMap<>();
        Optional<Ventas> ventasExiste = dao.findById(id);
        if (ventasExiste.isPresent()) {
            Ventas venta = ventasExiste.get();
            venta.setNombreApellido(ventas.getNombreApellido());
            venta.setDni(ventas.getDni());
            venta.setCorreo(ventas.getCorreo());
            venta.setFecha(ventas.getFecha());
            venta.setMedioPago(ventas.getMedioPago());
            venta.setEstado(ventas.getEstado());
            venta.setFilm(ventas.getFilm());
            dao.save(venta);
            respuesta.put("ventas", ventas);
            respuesta.put("mensaje", "Datos de la boleta modificado");
            respuesta.put("status", HttpStatus.CREATED);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }else {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarVentas(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Ventas> ventasExiste = dao.findById(id);
        if (ventasExiste.isPresent()) {
            Ventas ventas = ventasExiste.get();
            dao.delete(ventas);
            respuesta.put("mensaje", "Eliminado correctamente");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "Sin registros con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> listaVentasEstado() {
        return null;
    }

}
