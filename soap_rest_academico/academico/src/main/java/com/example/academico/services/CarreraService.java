package com.example.academico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.academico.models.CarreraModel;
import com.example.academico.repositories.ICarreraRepository;

@Service
public class CarreraService {
    
    @Autowired
    private ICarreraRepository carreraRepository;

    public List<CarreraModel> getAllCarreras() {
        return carreraRepository.findAll();
    }

    public Optional<CarreraModel>  getCarreraById(Long id_carrera) {
        return carreraRepository.findById(id_carrera);
    }

    public CarreraModel saveCarrera(CarreraModel carrera) {
        return carreraRepository.save(carrera);
    }

    public void deleteCarrera(Long id_carrera) {
        carreraRepository.deleteById(id_carrera);
    }

    
}