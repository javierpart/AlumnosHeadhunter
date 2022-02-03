package com.AlumnosOB.Alumnos.open.bootcamp.security.service;

import com.AlumnosOB.Alumnos.open.bootcamp.entity.Recruiter;
import com.AlumnosOB.Alumnos.open.bootcamp.entity.SuperAdmin;
import com.AlumnosOB.Alumnos.open.bootcamp.repository.RecruiterRepository;
import com.AlumnosOB.Alumnos.open.bootcamp.repository.SuperAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Autentica un usuario de la base de datos
 *
 * Authentication Manager llama al método loadUserByUsername de esta clase
 * para obtener los detalles del usuario de la base de datos cuando
 * se intente autenticar un usuario
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RecruiterRepository recRepository;

    @Autowired
    private SuperAdminRepository superRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SuperAdmin sAdmin = superRep.findByEmail(username);
        Recruiter rec = recRepository.findByEmail(username);
        if(sAdmin != null){
            return new org.springframework.security.core.userdetails.User(
                    sAdmin.getEmail(),sAdmin.getHashPassword(),new ArrayList<>());
        }

        return new org.springframework.security.core.userdetails.User(
                rec.getEmail(),rec.getHashPassword(),new ArrayList<>());
    }
}
