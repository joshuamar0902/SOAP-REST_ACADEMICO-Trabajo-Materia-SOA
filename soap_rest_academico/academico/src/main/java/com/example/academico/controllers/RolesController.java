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

import com.example.academico.models.RolesModel;
import com.example.academico.services.RolesService;

@RestController
@RequestMapping("/api/roles")
public class RolesController {
    @Autowired
    private RolesService rolesService;

    @GetMapping
    public List<RolesModel> getAllRoles() {
        return rolesService.getAllRoles();
    }

    @GetMapping("/{id_rol}")
    public ResponseEntity<RolesModel> getRolesById(@PathVariable Long id_rol) {
        Optional <RolesModel> roles = rolesService.getRolesById(id_rol);
        return roles.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());        
    }

    @PostMapping
    public RolesModel createRoles(@RequestBody RolesModel rol) {
        return rolesService.saveRoles(rol);
    }

    @PutMapping("/{id_rol}")
    public ResponseEntity<RolesModel> updateRoles(@PathVariable Long id_rol, @RequestBody RolesModel roleDetails) {
        Optional<RolesModel> roleOptional = rolesService.getRolesById(id_rol);
        if (roleOptional.isPresent()) {
            RolesModel roleToUpdate = roleOptional.get();
            roleToUpdate.setId_rol(roleDetails.getId_rol());
            roleToUpdate.setNombre_rol(roleDetails.getNombre_rol());
            RolesModel updatedRole = rolesService.saveRoles(roleToUpdate);
            return ResponseEntity.ok(updatedRole);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id_rol}")
    public ResponseEntity<Void> deleteRoles(@PathVariable Long id_rol) {
        Optional<RolesModel> role = rolesService.getRolesById(id_rol);
        if (role.isPresent()) {
            rolesService.deleteRoles(id_rol);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}