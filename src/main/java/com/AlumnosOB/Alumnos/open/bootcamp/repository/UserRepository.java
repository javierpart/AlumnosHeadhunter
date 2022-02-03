package com.AlumnosOB.Alumnos.open.bootcamp.repository;

import com.AlumnosOB.Alumnos.open.bootcamp.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    List<User> findAll(Specification<User> user);

    User findByPublicId(String publicId);


}
