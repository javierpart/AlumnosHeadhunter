package com.AlumnosOB.Alumnos.open.bootcamp.controller;


import com.AlumnosOB.Alumnos.open.bootcamp.Dto.TechnologyDto;
import com.AlumnosOB.Alumnos.open.bootcamp.Dto.UserDto;
import com.AlumnosOB.Alumnos.open.bootcamp.entity.Technology;
import com.AlumnosOB.Alumnos.open.bootcamp.entity.User;
import com.AlumnosOB.Alumnos.open.bootcamp.service.TechnologyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class TechnologyController {

    private final Logger log = LoggerFactory.getLogger(TechnologyController.class);
    @Autowired
    TechnologyService techService;

    @PreAuthorize("hasRole('ADMIN', 'RECRUITER')")
    @GetMapping("/tags")
    public ResponseEntity<List<Technology>> getAllTags(){
        return new ResponseEntity<>(techService.getAllTech(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN', 'RECRUITER')")
    @GetMapping("/tags/{id}")
    public ResponseEntity<Technology> getTagById(@PathVariable("id") String id){
        Optional<Technology> tagOpt = Optional.ofNullable(techService.findTechByPublicId(id));
        if (tagOpt.isPresent()){
            return ResponseEntity.ok(tagOpt.get());
        }else{
            log.warn("trying to get a non existing tag");
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/tags")
    public ResponseEntity createUser(@RequestBody TechnologyDto tag){
        techService.createTech(tag);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/tags/{id}")
    public ResponseEntity updateUser(@PathVariable("id") String id, @RequestBody TechnologyDto tag){
        techService.updateTechByPublicId(id, tag);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/tags/{id}")
    public ResponseEntity deleteUserByPublicId (@PathVariable("id") String id){
        Optional<Technology> tagOpt = Optional.ofNullable(techService.findTechByPublicId(id));
        if(tagOpt.isPresent()){
            techService.delTechByPublicId(id);
            return ResponseEntity.ok().build();
        }else{
            log.warn("trying to delete a non existing offer");
            return ResponseEntity.notFound().build();
        }
    }


}
