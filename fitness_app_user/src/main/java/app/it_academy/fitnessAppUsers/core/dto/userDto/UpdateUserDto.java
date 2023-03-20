package app.it_academy.fitnessAppUsers.core.dto.userDto;


import app.it_academy.fitnessAppUsers.domain.User;
import app.it_academy.fitnessAppUsers.domain.UserRole;
import app.it_academy.fitnessAppUsers.domain.UserStatus;


public class UpdateUserDto {

    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
    private String password;

    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UpdateUserDto combine(User user) {
        if (mail == null) {
            mail = user.getMail();
        }
        if (fio == null) {
            fio = user.getFio();
        }
        if (password == null) {
            password = user.getPassword();
        }
        if (status == null) {
            status = user.getStatus();
        }
        if (role == null) {
            role = user.getRole();
        }
        return this;
    }
}
