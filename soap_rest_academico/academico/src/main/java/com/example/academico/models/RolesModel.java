package com.example.academico.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "roles")
public class RolesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id_rol;


    @Enumerated(EnumType.STRING)
    @Column(name = "nombre_rol", nullable = false, unique = true)
    private Roles nombre_rol;


    public Long getId_rol() {
        return id_rol;
    }


    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }


    public Roles getNombre_rol() {
        return nombre_rol;
    }


    public void setNombre_rol(Roles nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    

    
}
