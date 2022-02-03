package com.AlumnosOB.Alumnos.open.bootcamp.repository;

import com.AlumnosOB.Alumnos.open.bootcamp.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

    Recruiter findByPublicId(String publicId);

    Recruiter findByEmail(String email);


}
