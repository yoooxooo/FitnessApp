package app.it_academy.fitnessAppMail.web.controllers;

import app.it_academy.fitnessAppMail.services.api.IMailService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/mail")
public class MailController {

    private final IMailService service;

    public MailController(IMailService service) {
        this.service = service;
    }

    @GetMapping (path = "/{mail}/{uuid}")
    public void sendSecret(@PathVariable("mail") String mail, @PathVariable("uuid") UUID uuid) throws MessagingException {
        service.sendSecret(mail, uuid);
    }
}
