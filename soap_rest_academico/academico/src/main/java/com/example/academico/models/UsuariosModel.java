package com.example.academico.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ALUMNOS")
public class UsuariosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id_usuario;

    @ManyToOne
    @JoinColumn(name = "id_carrera") // Esto crea la columna de la clave foránea en la tabla 'usuarios'
    private CarreraModel carrera;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fecha_nacimiento;

    @ManyToOne
    @JoinColumn(name = "id_curso", insertable = false, updatable = false)
    private CursoModel curso;

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public CarreraModel getCarrera() {
        return carrera;
    }

    public void setCarrera(CarreraModel carrera) {
        this.carrera = carrera;
    }

    public CursoModel getCurso() {
        return curso;
    }

    public void setCurso(CursoModel curso) {
        this.curso = curso;
    }




}