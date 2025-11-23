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

import com.example.academico.models.InscripcionModel;
import com.example.academico.services.InscripcionService;

@RestController
@RequestMapping("/api/inscripcion")
public class InscripcionController {
    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public List<InscripcionModel> getAllInscripciones() {
        return inscripcionService.getAllInscripciones();
    }

    @GetMapping("/{id_inscripcion}")
    public ResponseEntity<InscripcionModel> getInscripcionById(@PathVariable Long id_inscripcion) {
        Optional <InscripcionModel> inscripcion = inscripcionService.getInscripcionById(id_inscripcion);
        return inscripcion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());        
    }

    @PostMapping
    public InscripcionModel createInscripcion(@RequestBody InscripcionModel inscripcion) {
        return inscripcionService.saveInscripcion(inscripcion);
    }

    @PutMapping("/{id_inscripcion}")
    public ResponseEntity<InscripcionModel> updateInscripcion(@PathVariable Long id_inscripcion, @RequestBody InscripcionModel inscripcionDetails) {
        Optional<InscripcionModel> inscripcionOptional = inscripcionService.getInscripcionById(id_inscripcion);
        if (inscripcionOptional.isPresent()) {
            InscripcionModel inscripcionToUpdate = inscripcionOptional.get();
            inscripcionToUpdate.setId_inscripcion(inscripcionDetails.getId_inscripcion());
            inscripcionToUpdate.setFecha_inscripcion(inscripcionDetails.getFecha_inscripcion());
            inscripcionToUpdate.setUsuario(inscripcionDetails.getUsuario());
            inscripcionToUpdate.setCarrera(inscripcionDetails.getCarrera());
            InscripcionModel updatedInscripcion = inscripcionService.saveInscripcion(inscripcionToUpdate);
            return ResponseEntity.ok(updatedInscripcion);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id_inscripcion}")
    public ResponseEntity<Void> delete(@PathVariable Long id_inscripcion) {
        Optional<InscripcionModel> inscripcion = inscripcionService.getInscripcionById(id_inscripcion);
        if (inscripcion.isPresent()) {
            inscripcionService.deleteInscripcion(id_inscripcion);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}