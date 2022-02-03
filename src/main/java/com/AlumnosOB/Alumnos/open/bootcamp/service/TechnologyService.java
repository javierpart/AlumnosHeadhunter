package com.AlumnosOB.Alumnos.open.bootcamp.service;


import com.AlumnosOB.Alumnos.open.bootcamp.Dto.TechnologyDto;
import com.AlumnosOB.Alumnos.open.bootcamp.entity.Technology;

import com.AlumnosOB.Alumnos.open.bootcamp.repository.TechnologyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TechnologyService {

    private final Logger log = LoggerFactory.getLogger(TechnologyService.class);

    @Autowired
    TechnologyRepository techRepo;

    @Autowired
    GeneratorIdsService genIdService;


    //CRUD
    public List<Technology> getAllTech(){
        return techRepo.findAll();
    }

    public Technology findTechByPublicId(String publicId) {
        Technology tech = techRepo.findByPublicId(publicId);
        return tech;
    }

    public Technology createTech (TechnologyDto tech) {
        Technology newTech = new Technology();
        String id = genIdService.defaultPublicId();
        newTech.setPublicId(id);
        newTech.setName(tech.getName());
        techRepo.save(newTech);
        return newTech;
    }

    public Technology updateTechByPublicId(String id, TechnologyDto tech) {
        Technology update = techRepo.findByPublicId(id);
        update.setName(tech.getName());
        techRepo.save(update);
        return update;
    }

    public void delTechByPublicId (String id){
        Technology delete = techRepo.findByPublicId(id);
        if(delete.getUsers().size()==0){
            techRepo.delete(delete);
        }else{
            log.warn("Trying to delete a tag with a users list!");
        }

    }

    public Technology findTechByName(String name){
        return techRepo.findByName(name);
    }

}
