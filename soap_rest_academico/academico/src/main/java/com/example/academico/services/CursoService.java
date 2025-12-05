package com.example.academico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.academico.models.CursoModel;
import com.example.academico.repositories.ICursoRepository;

@Service
public class CursoService {
    
    @Autowired
    private ICursoRepository cursoRepository;

    public List<CursoModel> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Optional<CursoModel>  getCursoById(Long id_curso) {
        return cursoRepository.findById(id_curso);
    }

    public CursoModel saveCurso(CursoModel curso) {
        return cursoRepository.save(curso);
    }

    public void deleteCurso(Long id_curso) {
        cursoRepository.deleteById(id_curso);
    }

    
}
