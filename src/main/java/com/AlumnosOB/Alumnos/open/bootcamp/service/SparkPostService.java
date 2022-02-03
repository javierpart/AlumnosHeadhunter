package com.AlumnosOB.Alumnos.open.bootcamp.service;
import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import org.apache.log4j.BasicConfigurator;
import org.springframework.stereotype.Service;

@Service
public class SparkPostService {

    private final String API_KEY = "759ceaae16fbc7cc55ea694920446266825fab7c";

    public void sendInvitationEmail(String userEmail, String hashedEmail) throws SparkPostException {
        BasicConfigurator.configure();
        Client client = new Client(API_KEY);
        client.sendMessage(
                "contacto@obtest.one",
                userEmail,
                "Código de invitación",
                "Código hash",
                hashedEmail);
    }
}


