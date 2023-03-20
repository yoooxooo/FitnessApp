package app.it_academy.fitnessAppUsers.core.dto.userDto;

import app.it_academy.fitnessAppUsers.core.exceptions.ErrorObject;

import java.util.ArrayList;
import java.util.List;

public class LoginUserDto {

    private String mail;
    private String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ErrorObject> checkFields() {
        List<ErrorObject> errorFields = new ArrayList<>();
        if (getPassword() == null || getPassword().isEmpty()) {
            errorFields.add(new ErrorObject("Поле должно быть заполнено", "Password"));
        }
        if (getMail() == null || getMail().isEmpty()) {
            errorFields.add(new ErrorObject("Поле должно быть заполнено", "Mail"));
        }
        return errorFields;
    }
}
