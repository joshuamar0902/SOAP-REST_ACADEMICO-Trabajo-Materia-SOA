package com.example.academico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.academico.models.UsuariosModel;
import com.example.academico.repositories.IUsuariosRepository;

@Service
public class UsuariosService {
    
    @Autowired
    private IUsuariosRepository usuariosRepository;

    public List<UsuariosModel> getAllUsuarios() {
        return usuariosRepository.findAll();
    }

    public Optional<UsuariosModel>  getUsuariosById(Long id_usuario) {
        return usuariosRepository.findById(id_usuario);
    }

    public UsuariosModel saveUsuarios(UsuariosModel usuario) {
        return usuariosRepository.save(usuario);
    }

    public void deleteUsuarios(Long id_usuario) {
        usuariosRepository.deleteById(id_usuario);
    }

    
}