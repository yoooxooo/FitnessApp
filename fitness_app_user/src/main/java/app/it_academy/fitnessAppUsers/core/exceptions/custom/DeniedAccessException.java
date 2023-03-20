package app.it_academy.fitnessAppUsers.core.exceptions.custom;

import app.it_academy.fitnessAppUsers.core.exceptions.ErrorObject;

import java.util.List;

public class DeniedAccessException extends RuntimeException{

    public DeniedAccessException(String message) {
        super(message);
    }

}