package com.AlumnosOB.Alumnos.open.bootcamp.service;

import com.AlumnosOB.Alumnos.open.bootcamp.entity.Role;
import com.AlumnosOB.Alumnos.open.bootcamp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;


    public Role findByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
