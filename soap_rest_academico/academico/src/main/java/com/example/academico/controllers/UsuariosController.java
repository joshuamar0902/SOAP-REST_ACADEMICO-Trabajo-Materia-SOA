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

import com.example.academico.models.UsuariosModel;
import com.example.academico.services.UsuariosService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
    @Autowired
    private UsuariosService usuariosService;

    @GetMapping
    public List<UsuariosModel> getAllUsuarios() {
        return usuariosService.getAllUsuarios();
    }

    @GetMapping("/{id_usuario}")
    public ResponseEntity<UsuariosModel> getUsuariosById(@PathVariable Long id_usuario) {
        Optional <UsuariosModel> usuario = usuariosService.getUsuariosById(id_usuario);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());        
    }

    @PostMapping
    public UsuariosModel createUsuarios(@RequestBody UsuariosModel usuario) {
        return usuariosService.saveUsuarios(usuario);
    }

    @PutMapping("/{id_usuario}")
    public ResponseEntity<UsuariosModel> updateUsuarios(@PathVariable Long id_usuario, @RequestBody UsuariosModel usuarioDetails) {
        Optional<UsuariosModel> usuarioOptional = usuariosService.getUsuariosById(id_usuario);
        if (usuarioOptional.isPresent()) {
            UsuariosModel usuarioToUpdate = usuarioOptional.get();
            usuarioToUpdate.setId_usuario(usuarioDetails.getId_usuario());
            usuarioToUpdate.setCarrera(usuarioDetails.getCarrera());
            usuarioToUpdate.setNombre(usuarioDetails.getNombre());
            usuarioToUpdate.setApellido(usuarioDetails.getApellido());
            usuarioToUpdate.setEmail(usuarioDetails.getEmail());
            usuarioToUpdate.setFecha_nacimiento(usuarioDetails.getFecha_nacimiento());
            UsuariosModel updatedUsuario = usuariosService.saveUsuarios(usuarioToUpdate);
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id_usuario}")
    public ResponseEntity<Void> deleteUsuarios(@PathVariable Long id_usuario) {
        Optional<UsuariosModel> usuario = usuariosService.getUsuariosById(id_usuario);
        if (usuario.isPresent()) {
            usuariosService.deleteUsuarios(id_usuario);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}