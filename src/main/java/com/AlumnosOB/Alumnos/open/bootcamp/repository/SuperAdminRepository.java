package com.AlumnosOB.Alumnos.open.bootcamp.repository;

import com.AlumnosOB.Alumnos.open.bootcamp.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin,Long> {
    SuperAdmin findByEmail(String username);
}
