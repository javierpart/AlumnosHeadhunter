package com.AlumnosOB.Alumnos.open.bootcamp;

import com.AlumnosOB.Alumnos.open.bootcamp.entity.SuperAdmin;
import com.AlumnosOB.Alumnos.open.bootcamp.repository.SuperAdminRepository;
import com.sparkpost.exception.SparkPostException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AlumnosOpenBootcampApplication {

	public static void main(String[] args) throws SparkPostException { //SpringApplication.run(AlumnosOpenBootcampApplication.class, args);

		ApplicationContext context = SpringApplication.run(AlumnosOpenBootcampApplication.class, args);
//		SuperAdminService adminService = context.getBean(SuperAdminService.class);
//
//		TechnologyService techServ = context.getBean(TechnologyService.class);
//
//		TechnologyDto tag = new TechnologyDto();
//		tag.setName("Java");
//
//		techServ.createTech(tag);

//		adminService.sendInvitationEmail("javierPartidavalle@gmail.com");
//		RecruiterRepository recRepo = context.getBean(RecruiterRepository.class);
//		Recruiter rec = new Recruiter();
//		rec.setPublicId("5nana");
//		rec.setHashPassword(bcryptEncoder.encode("hola"));
//		rec.setEmail("javi15@gmail.com");
//		recRepo.save(rec);
//		PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
//		SuperAdmin admin1 = new SuperAdmin();
//		admin1.setEmail("hola2@gmail.com");
//		admin1.setHashPassword(encoder.encode("hola"));
//		SuperAdminRepository admin2 = context.getBean(SuperAdminRepository.class);
//		admin2.save(admin1);

	}


}
