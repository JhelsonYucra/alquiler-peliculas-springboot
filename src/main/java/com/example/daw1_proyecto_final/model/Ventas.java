package com.example.daw1_proyecto_final.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@Entity
@Table(name="ventas")
@EntityListeners(AuditingEntityListener.class)
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ventaId;               // Identificador único
    private String nombreApellido;         // Nombre del Cliente
    private String dni;            // DNI del Cliente
    private String correo;
    private Date fecha;            // Fecha asociada al Cliente
    private String medioPago;
    private String estado;         // Estado de la boleta (A: Activo, I: Inactivo)

    // Relación con la entidad Film
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false) // Define la clave foránea en la tabla "ventas"
    private Film film; // Película asociada a la venta
}
