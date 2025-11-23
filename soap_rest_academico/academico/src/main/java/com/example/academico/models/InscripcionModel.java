package com.example.academico.models;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "inscripciones") // Debe coincidir con el nombre de la tabla creada por Python
public class InscripcionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscripcion")
    private Long id_inscripcion;

    // Relación ManyToOne con Usuarios (asumo que ya tienes UsuariosModel)
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false) 
    private UsuariosModel usuario; // Usamos el objeto completo para la relación

    // Relación ManyToOne con Carrera (asumo que ya tienes CarreraModel)
    @ManyToOne
    @JoinColumn(name = "id_carrera", nullable = false) 
    private CarreraModel carrera; // Usamos el objeto completo para la relación

    @Column(name = "fecha_inscripcion", nullable = false)
    private Date fecha_inscripcion;
    
    // --- Getters y Setters ---
    
    public Long getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(Long id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public UsuariosModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuariosModel usuario) {
        this.usuario = usuario;
    }

    public CarreraModel getCarrera() {
        return carrera;
    }

    public void setCarrera(CarreraModel carrera) {
        this.carrera = carrera;
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(Date fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }
}