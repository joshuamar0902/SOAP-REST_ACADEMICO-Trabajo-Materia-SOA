package com.example.academico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.academico.models.CarreraModel;

@Repository

public interface ICarreraRepository extends JpaRepository<CarreraModel, Long> {
    
}
