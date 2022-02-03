package com.AlumnosOB.Alumnos.open.bootcamp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="ob_technology")
public class Technology implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id;
    @Column(name = "public_id", unique=true)
    String publicId;
    String name;
    @JsonIgnore
    @ManyToMany( cascade = CascadeType.ALL, mappedBy = "tags")
    List<User> users = new ArrayList<User>();


}
