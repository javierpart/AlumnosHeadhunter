package com.AlumnosOB.Alumnos.open.bootcamp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Data
@Entity
@Table(name="recruiter")
public class Recruiter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id;
    @Column(name = "public_id", unique=true)
    String publicId;
    String name;
    @Column(name = "last_name")
    String lastName;
    @JsonIgnore
    @Column(name = "hash_password")
    String hashPassword;
    String company;
    @Column(name = "phone_number", unique=true)
    String phoneNumber;
    @Column(name = "profile_imageurl")
    String profileImageUrl;
    @Column(name = "register_attempts", nullable = false)
    int registerAttempts;
    @Column(name = "is_registered")
    boolean isRegistered;
    @Column(unique=true)
    String email;
    @JsonIgnore
    @Column(name = "hashed_email")
    String hashedEmail;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "RECRUITER_ROLES",
            joinColumns = {
                    @JoinColumn(name = "RECRUITER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID") })
    private Set<Role> roles;

}
