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

import com.example.academico.models.CarreraModel;

import com.example.academico.services.CarreraService;

@RestController
@RequestMapping("/api/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public List<CarreraModel> getAllCarreras() {
        return carreraService.getAllCarreras();
    }

    @GetMapping("/{id_carrera}")
    public ResponseEntity<CarreraModel> getCarreraById(@PathVariable Long id_carrera) {
        Optional <CarreraModel> carrera = carreraService.getCarreraById(id_carrera);
        return carrera.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());        
    }

    @PostMapping
    public CarreraModel createCarrera(@RequestBody CarreraModel id_carrera) {
        return carreraService.saveCarrera(id_carrera);
    }

    @PutMapping("/{id_carrera}")
    public ResponseEntity<CarreraModel> updateCarrera(@PathVariable Long id_carrera, @RequestBody CarreraModel carreraDetails) {
        Optional<CarreraModel> carreraOptional = carreraService.getCarreraById(id_carrera);
        if (carreraOptional.isPresent()) {
            CarreraModel carreraToUpdate = carreraOptional.get();
            carreraToUpdate.setId_carrera(carreraDetails.getId_carrera());
            carreraToUpdate.setNombre_carrera(carreraDetails.getNombre_carrera());
            CarreraModel updatedCarrera = carreraService.saveCarrera(carreraToUpdate);
            return ResponseEntity.ok(updatedCarrera);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id_carrera}")
    public ResponseEntity<Void> deleteCarrera(@PathVariable Long id_carrera) {
        Optional<CarreraModel> carrera = carreraService.getCarreraById(id_carrera);
        if (carrera.isPresent()) {
            carreraService.deleteCarrera(id_carrera);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}