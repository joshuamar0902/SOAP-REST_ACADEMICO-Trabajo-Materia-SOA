package com.example.academico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.academico.models.UsuariosModel;

@Repository

public interface IUsuariosRepository extends JpaRepository<UsuariosModel, Long> {
    
}
