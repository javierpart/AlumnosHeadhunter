package com.AlumnosOB.Alumnos.open.bootcamp.controller;

import com.AlumnosOB.Alumnos.open.bootcamp.entity.Recruiter;
import com.AlumnosOB.Alumnos.open.bootcamp.repository.RecruiterRepository;
import com.AlumnosOB.Alumnos.open.bootcamp.security.jwt.JwtTokenUtil;
import com.AlumnosOB.Alumnos.open.bootcamp.security.payload.JwtResponse;
import com.AlumnosOB.Alumnos.open.bootcamp.security.payload.LoginRequest;
import com.AlumnosOB.Alumnos.open.bootcamp.security.payload.MessageResponse;
import com.AlumnosOB.Alumnos.open.bootcamp.security.payload.RegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para llevar a cabo la autenticación utilizando JWT
 *
 * Se utiliza AuthenticationManager para autenticar las credenciales que son el
 * usuario y password que llegan por POST en el cuerpo de la petición
 *
 * Si las credenciales son válidas se genera un token JWT como respuesta
 */
// @CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final RecruiterRepository recRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthenticationManager authManager,
                          RecruiterRepository recRepository,
                          PasswordEncoder encoder,
                          JwtTokenUtil jwtTokenUtil){
        this.authManager = authManager;
        this.recRepository = recRepository;
        this.encoder = encoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        // UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest signUpRequest) {
        Recruiter rec =recRepository.findByEmail(signUpRequest.getEmail());

        if (rec.getHashedEmail().equals(signUpRequest.getInvitationHash()) && rec.isRegistered()==false && rec.getRegisterAttempts()<3) {
            rec.setHashPassword(encoder.encode(signUpRequest.getPassword()));
            rec.setRegistered(true);
            recRepository.save(rec);
            return ResponseEntity.ok(new MessageResponse("Recruiter registered successfully!"));
        }else if(!rec.getHashedEmail().equals(signUpRequest.getInvitationHash()) && rec.isRegistered()==false && rec.getRegisterAttempts()<3){
            rec.setRegisterAttempts(rec.getRegisterAttempts()+1);
            recRepository.save(rec);
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: invitation code worng, you only have 3 attempts!"));
        }else if(!rec.getHashedEmail().equals(signUpRequest.getInvitationHash()) && rec.isRegistered()==false && rec.getRegisterAttempts()>=3) {
            recRepository.delete(rec);
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: invitation code worng, account is deleted!"));
        }else if(!rec.getHashedEmail().equals(signUpRequest.getInvitationHash()) && rec.isRegistered()==true){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The user is already registered!"));
        }else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: something went wrong!"));
        }


    }
}