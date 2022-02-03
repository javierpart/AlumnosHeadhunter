package com.AlumnosOB.Alumnos.open.bootcamp.controller;


import com.AlumnosOB.Alumnos.open.bootcamp.Dto.RecruiterDto;
import com.AlumnosOB.Alumnos.open.bootcamp.Dto.UserDto;
import com.AlumnosOB.Alumnos.open.bootcamp.entity.Recruiter;
import com.AlumnosOB.Alumnos.open.bootcamp.entity.User;
import com.AlumnosOB.Alumnos.open.bootcamp.security.jwt.JwtTokenUtil;
import com.AlumnosOB.Alumnos.open.bootcamp.service.RecruiterService;
import com.AlumnosOB.Alumnos.open.bootcamp.service.SparkPostService;
import com.sparkpost.exception.SparkPostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/api/recruiter")
@RestController
public class RecruiterController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    RecruiterService recService;

    @Autowired
    SparkPostService spark;



    @PreAuthorize("hasRole('ADMIN', 'RECRUITER')")
    @PutMapping
    public ResponseEntity<Recruiter> updateRecruiter(@RequestBody RecruiterDto recruiter )  {
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        Recruiter rec = recService.findRecByPublicId(recruiter.getPublicId());
        if (rec.getEmail().equals(userName)) {
            recService.update(recruiter);
            return ResponseEntity.ok().build();
        }else{
            log.warn("trying to update another recruiter");
            return ResponseEntity.notFound().build();
        }

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{email}")
    public ResponseEntity<Recruiter> createRecruiter(@PathVariable("email") String email) throws SparkPostException {
        Recruiter rec = recService.create(email);
        spark.sendInvitationEmail(rec.getEmail(), rec.getHashedEmail());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRecruiterByPublicId (@PathVariable("id") String id){
        Optional<Recruiter> recOpt = Optional.ofNullable(recService.findRecByPublicId(id));
        if(recOpt.isPresent()){
            recService.delRecByPublicId(id);
            return ResponseEntity.ok().build();
        }else{
            log.warn("trying to delete a non existing recruiter");
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<Recruiter>> getAll (){
        return new ResponseEntity<>(recService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity getById (@PathVariable("id") String id){
        Optional<Recruiter> recOpt = Optional.ofNullable(recService.findRecByPublicId(id));
        if (recOpt.isPresent()){
            return ResponseEntity.ok(recOpt.get());
        }else{
            log.warn("trying to get a non existing recruiter");
            return ResponseEntity.notFound().build();
        }
    }
}
