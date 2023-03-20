package app.it_academy.fitnessAppAudit.exceptions.custom;

public class DeniedAccessException extends RuntimeException{

    public DeniedAccessException(String message) {
        super(message);
    }

}