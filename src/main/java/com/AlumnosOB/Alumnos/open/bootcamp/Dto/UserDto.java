package com.AlumnosOB.Alumnos.open.bootcamp.Dto;

import com.AlumnosOB.Alumnos.open.bootcamp.entity.Remote;
import lombok.Data;
import java.util.List;

@Data
public class UserDto {

    String userPublicId;
    String name;
    String lastName;
    String email;
    String phoneNumber;
    String country;
    String city;
    Boolean cityTransfer;
    Remote remote;
    String cvUrl;
    String profileImageUrl;
    List<TechnologyDto> tags;
}
