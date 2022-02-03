package com.AlumnosOB.Alumnos.open.bootcamp.service;

import com.AlumnosOB.Alumnos.open.bootcamp.Dto.RecruiterDto;
import com.AlumnosOB.Alumnos.open.bootcamp.Dto.RecruiterListDto;
import com.AlumnosOB.Alumnos.open.bootcamp.entity.Recruiter;
import com.AlumnosOB.Alumnos.open.bootcamp.entity.Role;
import com.AlumnosOB.Alumnos.open.bootcamp.entity.User;
import com.AlumnosOB.Alumnos.open.bootcamp.repository.RecruiterRepository;
import com.AlumnosOB.Alumnos.open.bootcamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecruiterService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RecruiterRepository recruiterRepo;

    @Autowired
    GeneratorIdsService generatorService;

    @Autowired
    RoleService roleService;




    public List<Recruiter> findAllRec(){
        return recruiterRepo.findAll();
    }

    public Recruiter findRecByPublicId(String publicId){
        return recruiterRepo.findByPublicId(publicId);
    }

    public void delRecByPublicId(String publicId){
        Recruiter rec = findRecByPublicId(publicId);
        recruiterRepo.delete(rec);
    }

    public Recruiter update(RecruiterDto recruiter)  {
        Recruiter rec = findRecByPublicId(recruiter.getPublicId());
        rec.setName(recruiter.getName());
        rec.setLastName(recruiter.getLastName());
        rec.setCompany(recruiter.getCompany());
        rec.setPhoneNumber(recruiter.getPhoneNumber());
        rec.setProfileImageUrl(recruiter.getProfileImageUrl());

        recruiterRepo.save(rec);
        return rec;
    }

    public Recruiter create(String email){
        Recruiter rec = new Recruiter();
        rec.setPublicId(generatorService.defaultPublicId());
        rec.setHashedEmail(encoder.encode(email.toLowerCase()));
        rec.setRegistered(false);
        rec.setEmail(email.toLowerCase());
        rec.setRegisterAttempts(Integer.valueOf(0) );
        Role role = roleService.findByName("RECRUITER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        rec.setRoles(roleSet);
        recruiterRepo.save(rec);
        return rec;

    }


    public List<Recruiter> getAll() {
        return recruiterRepo.findAll();
    }
}
