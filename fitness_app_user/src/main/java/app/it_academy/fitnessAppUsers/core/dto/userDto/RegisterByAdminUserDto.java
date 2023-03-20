package app.it_academy.fitnessAppUsers.core.dto.userDto;


import app.it_academy.fitnessAppUsers.core.exceptions.ErrorObject;
import app.it_academy.fitnessAppUsers.domain.UserRole;

import java.util.ArrayList;
import java.util.List;

public class RegisterByAdminUserDto {

    private String mail;
    private String fio;
    private UserRole role;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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
        if (getFio() == null || getFio().isEmpty()) {
            errorFields.add(new ErrorObject("Поле должно быть заполнено", "Fio"));
        }
        if (getRole() == null) {
            errorFields.add(new ErrorObject("Поле должно быть заполнено", "Role"));
        }
        return errorFields;
    }
}
