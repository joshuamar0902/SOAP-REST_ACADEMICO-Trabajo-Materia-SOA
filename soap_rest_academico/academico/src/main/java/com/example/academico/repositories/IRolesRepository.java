package com.example.academico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.academico.models.RolesModel;

@Repository

public interface IRolesRepository extends JpaRepository<RolesModel, Long> {
    
}
