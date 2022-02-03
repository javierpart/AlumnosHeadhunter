package com.AlumnosOB.Alumnos.open.bootcamp.repository;

import com.AlumnosOB.Alumnos.open.bootcamp.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findRoleByName(String name);
}
