package com.example.academico.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.academico.models.CursoModel;
import com.example.academico.services.CursoService;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<CursoModel> getAllCursos() {
        return cursoService.getAllCursos();
    }

    @GetMapping("/{id_curso}")
    public ResponseEntity<CursoModel> getCursoById(@PathVariable Long id_curso) {
        Optional <CursoModel> curso = cursoService.getCursoById(id_curso);
        return curso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());        
    }

    @PostMapping
    public CursoModel createCurso(@RequestBody CursoModel curso) {
        return cursoService.saveCurso(curso);
    }

    @PutMapping("/{id_curso}")
    public ResponseEntity<CursoModel> updateCurso(@PathVariable Long id_curso, @RequestBody CursoModel cursoDetails) {
        Optional<CursoModel> cursoOptional = cursoService.getCursoById(id_curso);
        if (cursoOptional.isPresent()) {
            CursoModel cursoToUpdate = cursoOptional.get();
            cursoToUpdate.setId_curso(cursoDetails.getId_curso());
            cursoToUpdate.setNombre(cursoDetails.getNombre());
            cursoToUpdate.setDescripcion(cursoDetails.getDescripcion());
            cursoToUpdate.setCreditos(cursoDetails.getCreditos());
            cursoToUpdate.setSemestre(cursoDetails.getSemestre());
            cursoToUpdate.setEstado(cursoDetails.getEstado());
            cursoToUpdate.setFecha_creacion(cursoDetails.getFecha_creacion());
            CursoModel updatedCurso = cursoService.saveCurso(cursoToUpdate);
            return ResponseEntity.ok(updatedCurso);

    
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id_curso}")
    public ResponseEntity<Void> delete(@PathVariable Long id_curso) {
        Optional<CursoModel> curso = cursoService.getCursoById(id_curso);
        if (curso.isPresent()) {
            cursoService.deleteCurso(id_curso);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}