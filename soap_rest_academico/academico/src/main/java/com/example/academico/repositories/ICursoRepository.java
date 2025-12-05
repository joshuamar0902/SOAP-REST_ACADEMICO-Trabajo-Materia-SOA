package com.example.academico.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.academico.models.CursoModel;

@Repository

public interface ICursoRepository extends JpaRepository<CursoModel, Long>{
    
}
