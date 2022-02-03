package com.AlumnosOB.Alumnos.open.bootcamp.controller;

import com.AlumnosOB.Alumnos.open.bootcamp.Dto.UserDto;
import com.AlumnosOB.Alumnos.open.bootcamp.entity.Remote;
import com.AlumnosOB.Alumnos.open.bootcamp.entity.User;
import com.AlumnosOB.Alumnos.open.bootcamp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('ADMIN', 'RECRUITER')")
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN', 'RECRUITER')")
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserByPublicID(@PathVariable("id") String id) {
        Optional<User> userOpt = Optional.ofNullable(userService.getByPublicId(id));
        if (userOpt.isPresent()){
            return ResponseEntity.ok(userOpt.get());
        }else{
            log.warn("trying to get a non existing user");
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN', 'RECRUITER')")
    @GetMapping("/user/{country}/{city}/{remote}/{transfer}")
    public ResponseEntity<List<User>> getUsersByCity(@PathVariable("country") String country ,@PathVariable("city") String city, @PathVariable("remote") Remote remote ,@PathVariable("transfer") boolean transfer ){
        return new ResponseEntity<>(userService.getFilteredUsers(country, city, remote, transfer), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody UserDto user){
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/user/{id}")
    public ResponseEntity updateUser(@PathVariable("id") String id, @RequestBody UserDto user) throws Exception {
        userService.updateUserByPublicId(id, user);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/user/{id}")
    public ResponseEntity deleteUserByPublicId (@PathVariable("id") String id){
        Optional<User> userOpt = Optional.ofNullable(userService.getByPublicId(id));
        if(userOpt.isPresent()){
            userService.deleteByPublicId(id);
            return ResponseEntity.ok().build();
        }else{
            log.warn("trying to delete a non existing user");
            return ResponseEntity.notFound().build();
        }
    }
}
