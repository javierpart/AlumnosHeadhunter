package com.AlumnosOB.Alumnos.open.bootcamp.service;


import com.AlumnosOB.Alumnos.open.bootcamp.Dto.UserDto;
import com.AlumnosOB.Alumnos.open.bootcamp.entity.Remote;
import com.AlumnosOB.Alumnos.open.bootcamp.entity.Technology;
import com.AlumnosOB.Alumnos.open.bootcamp.entity.User;
import com.AlumnosOB.Alumnos.open.bootcamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.AlumnosOB.Alumnos.open.bootcamp.Spec.UserSpec.*;
import static org.springframework.data.jpa.domain.Specification.where;



@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    GeneratorIdsService generatorService;

    @Autowired
    TechnologyService techService;

    public User getByPublicId(String userPublicId) {
        return userRepo.findByPublicId(userPublicId);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User createUser(UserDto user) {
            String publicId =generatorService.defaultPublicId();
            User newUser = new User();
            newUser.setPublicId(publicId);
            newUser.setName(user.getName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            newUser.setPhoneNumber(user.getPhoneNumber());
            newUser.setCountry(user.getCountry());
            newUser.setCity(user.getCity());
            newUser.setCityTransfer(user.getCityTransfer());
            newUser.setRemote(user.getRemote());
            newUser.setCvUrl(user.getCvUrl());
            newUser.setProfileImageUrl(user.getProfileImageUrl());

            List<Technology> tags = new ArrayList<>();
            user.getTags().stream().forEach( tag -> {Technology newTech = techService.findTechByPublicId(tag.getPublicId());
                tags.add(newTech);
            });
            newUser.setTags(tags);
            userRepo.save(newUser);
            return newUser;

    }

    public User updateUserByPublicId(String publicId, UserDto user) throws Exception {
        User update = userRepo.findByPublicId(publicId);
        if (update == null){
            throw new Exception("user doesn't exist");
        }
        update.setName(user.getName());
        update.setLastName(user.getLastName());
        update.setEmail(user.getEmail());
        update.setPhoneNumber(user.getPhoneNumber());
        update.setCountry(user.getCountry());
        update.setCity(user.getCity());
        update.setCityTransfer(user.getCityTransfer());
        update.setRemote(user.getRemote());
        update.setCvUrl(user.getCvUrl());
        update.setProfileImageUrl(user.getProfileImageUrl());
        List<Technology> tags = new ArrayList<>();
        user.getTags().stream().forEach( tag -> {Technology newTech = techService.findTechByName(tag.getName());
            tags.add(newTech);
        });
        update.setTags(tags);

        userRepo.save(update);
        return update;
    }

    public void deleteByPublicId (String publicId){
        User user = userRepo.findByPublicId(publicId);
        userRepo.delete(user);
    }


    public List<User> getFilteredUsers(String country, String city, Remote remote, boolean transfer) {
        List<User> users;
        if (country.equals("ALL") && city.equals("ALL")) {
            users = userRepo.findAll(where(isRemote(remote)).and(isTransfer(transfer)));
        } else if (country.equals("ALL")) {
            users = userRepo.findAll(where(isRemote(remote)).and(isCity(city).and(isTransfer(transfer))));
        } else if (city.equals("ALL")) {
            users = userRepo.findAll(where(isRemote(remote)).and(isCountry(country)).and(isTransfer(transfer)));

        } else {
            users = userRepo.findAll(where(isRemote(remote)).and(isCity(city).and(isCountry(country)).and(isTransfer(transfer))));

        }
        return users;
    }


}
