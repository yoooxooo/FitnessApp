package app.it_academy.fitnessAppUsers.core.exceptions.custom;

import app.it_academy.fitnessAppUsers.core.exceptions.ErrorObject;

import java.util.List;

public class FieldValidationException extends RuntimeException{

    private final List<ErrorObject> errors;

    public FieldValidationException(String message, List<ErrorObject> errors) {
        super(message);
        this.errors = errors;
    }

    public List<ErrorObject> getErrors() {
        return errors;
    }
}
