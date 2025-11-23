package com.example.academico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.academico.models.InscripcionModel;

@Repository

public interface IInscripcionRepository extends JpaRepository<InscripcionModel, Long> {
    
}
