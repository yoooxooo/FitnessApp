package app.it_academy.fitnessAppMail.services.api;

import jakarta.mail.MessagingException;

import java.util.UUID;

public interface IMailService {

    public void sendSecret(String mail, UUID uuid) throws MessagingException;

    public void sendConfirmationLinkAsync(String address, String token) throws MessagingException;
}
