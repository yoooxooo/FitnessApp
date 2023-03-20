package app.it_academy.fitnessAppUsers.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "dt_create")
    private Instant creationDate;

    @Column(name = "dt_update")
    @Version
    private Instant updateDate;

    @Column(name = "mail", unique = true)
    private String mail;

    @Column(name = "fio")
    private String fio;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "password")
    private String password;

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
