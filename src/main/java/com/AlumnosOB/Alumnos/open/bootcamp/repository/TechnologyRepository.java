package com.AlumnosOB.Alumnos.open.bootcamp.repository;

import com.AlumnosOB.Alumnos.open.bootcamp.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology,Long> {
    Technology findByPublicId(String publicId);

    Technology findByName(String name);
}
