package app.it_academy.fitnessAppMail.services;

import app.it_academy.fitnessAppMail.config.propeties.MailProperties;
import app.it_academy.fitnessAppMail.services.api.IMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class MailService implements IMailService {

    private final JavaMailSender mailSender;
    private final JobScheduler jobScheduler;
    private final MailProperties properties;

    public MailService(JavaMailSender mailSender, JobScheduler jobScheduler,
                        MailProperties properties) {

        this.mailSender = mailSender;
        this.jobScheduler = jobScheduler;
        this.properties = properties;
    }

    @Override
    public void sendSecret(String address, UUID uuid) throws MessagingException {
        StringBuilder str = new StringBuilder(uuid.toString());
        String confirmationToken = str.reverse().toString();
        this.jobScheduler.enqueue(() -> sendConfirmationLinkAsync(address, confirmationToken));
    }

    @Job(name = "Confirmation Link", retries = 3)
    public void sendConfirmationLinkAsync(String address, String token) throws MessagingException {

        MimeMessage confirmationMail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(confirmationMail, true);

        helper.setFrom(properties.getSenderAddress());
        helper.setTo(address);
        helper.setSubject(properties.getConfirmationSubject());
        String text = properties.getConfirmationTextFirst() + createLink(token, address) + properties.getConfirmationTextSecond();
        helper.setText(text);

        mailSender.send(confirmationMail);
    }

    public String createLink(String token, String address) {
        return properties.getLinkPart() + "?code=" + token + "&mail=" + address;
    }
}
