package app.it_academy.fitnessAppAudit.core.dto.usersDto;



import app.it_academy.fitnessAppAudit.domain.UserRole;
import app.it_academy.fitnessAppAudit.domain.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.UUID;

public class FullUserDto {

    private UUID id;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant dt_create;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant dt_update;
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;

    public FullUserDto() {

    }

    public UUID getId() {
        return id;
    }


    public Instant getDt_create() {
        return dt_create;
    }

    public void setDt_create(Instant dt_create) {
        this.dt_create = dt_create;
    }

    public Instant getDt_update() {
        return dt_update;
    }

    public void setDt_update(Instant dt_update) {
        this.dt_update = dt_update;
    }

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

    public void setId(UUID id) {
        this.id = id;
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
}
