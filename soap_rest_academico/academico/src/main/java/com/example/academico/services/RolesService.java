package com.example.academico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.academico.models.RolesModel;
import com.example.academico.repositories.IRolesRepository;

@Service
public class RolesService {
    
    @Autowired
    private IRolesRepository rolesRepository;

    public List<RolesModel> getAllRoles() {
        return rolesRepository.findAll();
    }

    public Optional<RolesModel>  getRolesById(Long id_rol) {
        return rolesRepository.findById(id_rol);
    }

    public RolesModel saveRoles(RolesModel rol) {
        return rolesRepository.save(rol);
    }

    public void deleteRoles(Long id_rol) {
        rolesRepository.deleteById(id_rol);
    }

    
}