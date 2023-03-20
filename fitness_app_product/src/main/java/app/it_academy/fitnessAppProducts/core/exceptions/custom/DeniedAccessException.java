package app.it_academy.fitnessAppProducts.core.exceptions.custom;

public class DeniedAccessException extends RuntimeException{

    public DeniedAccessException(String message) {
        super(message);
    }

}