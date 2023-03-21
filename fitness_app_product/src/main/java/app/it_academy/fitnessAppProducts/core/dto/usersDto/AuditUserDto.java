package app.it_academy.fitnessAppProducts.core.dto.usersDto;

import app.it_academy.fitnessAppProducts.domain.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class AuditUserDto {

    @JsonProperty(value = "uuid")
    private UUID userId;

    private String mail;

    private String fio;

    private UserRole role;

    public AuditUserDto() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

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
}
