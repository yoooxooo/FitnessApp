package app.it_academy.fitnessAppMail.config.propeties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "mail")
@Component
public class MailProperties {

    private String senderAddress;

    private String confirmationTextSecond;

    private String confirmationTextFirst;

    private String confirmationSubject;

    private String linkPart;

    public String getLinkPart() {
        return linkPart;
    }

    public void setLinkPart(String linkPart) {
        this.linkPart = linkPart;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getConfirmationTextSecond() {
        return confirmationTextSecond;
    }

    public void setConfirmationTextSecond(String confirmationTextSecond) {
        this.confirmationTextSecond = confirmationTextSecond;
    }

    public String getConfirmationTextFirst() {
        return confirmationTextFirst;
    }

    public void setConfirmationTextFirst(String confirmationTextFirst) {
        this.confirmationTextFirst = confirmationTextFirst;
    }

    public String getConfirmationSubject() {
        return confirmationSubject;
    }

    public void setConfirmationSubject(String confirmationSubject) {
        this.confirmationSubject = confirmationSubject;
    }
}
