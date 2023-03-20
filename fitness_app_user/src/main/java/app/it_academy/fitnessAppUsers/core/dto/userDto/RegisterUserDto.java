package app.it_academy.fitnessAppUsers.core.dto.userDto;


import app.it_academy.fitnessAppUsers.core.exceptions.ErrorObject;

import java.util.ArrayList;
import java.util.List;

public class RegisterUserDto {

    private String mail;
    private String fio;
    private String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ErrorObject> checkFields() {
        List<ErrorObject> errorFields = new ArrayList<>();
        if (getFio() == null || getFio().isEmpty()) {
            errorFields.add(new ErrorObject("Поле должно быть заполнено", "Fio"));
        }
        if (getPassword() == null || getPassword().isEmpty()) {
            errorFields.add(new ErrorObject("Поле должно быть заполнено", "Password"));
        }
        if (getMail() == null || getMail().isEmpty()) {
            errorFields.add(new ErrorObject("Поле должно быть заполнено", "Mail"));
        }
    if (getMail().split("@").length != 2 || (getMail().split("@")[1]).split("\\.").length != 2) {
            errorFields.add(new ErrorObject("Неправильный формат адреса почты", "Mail"));
        }
        return errorFields;
    }
}
