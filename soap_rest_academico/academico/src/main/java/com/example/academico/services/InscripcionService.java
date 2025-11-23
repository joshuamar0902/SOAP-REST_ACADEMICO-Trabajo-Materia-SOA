package com.example.academico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.academico.models.InscripcionModel;
import com.example.academico.repositories.IInscripcionRepository;

@Service
public class InscripcionService {
    
    @Autowired
    private IInscripcionRepository inscripcionRepository;

    public List<InscripcionModel> getAllInscripciones() {
        return inscripcionRepository.findAll();
    }

    public Optional<InscripcionModel>  getInscripcionById(Long id_inscripcion) {
        return inscripcionRepository.findById(id_inscripcion);
    }

    public InscripcionModel saveInscripcion(InscripcionModel inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public void deleteInscripcion(Long id_inscripcion) {
        inscripcionRepository.deleteById(id_inscripcion);
    }

    
}