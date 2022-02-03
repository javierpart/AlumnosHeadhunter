package com.AlumnosOB.Alumnos.open.bootcamp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="ob_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id;
    @Column(name = "public_id", unique=true)
    String publicId;
    String name;
    @Column(name = "last_name")
    String lastName;
    @Column(unique=true)
    String email;
    @Column(name = "phone_number", unique=true)
    String phoneNumber;
    String country;
    String city;
    @Column(name = "city_transfer")
    boolean cityTransfer;
    @Enumerated(value = EnumType.STRING)
    Remote remote ;
    @Column(name = "cv_url")
    String cvUrl;
    @Column(name = "image_url")
    String profileImageUrl;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="users_tags")
    List<Technology> tags = new ArrayList<Technology>();

    @PreRemove
    public void removeTags(){
        this.tags = new ArrayList<>();
    }
}
