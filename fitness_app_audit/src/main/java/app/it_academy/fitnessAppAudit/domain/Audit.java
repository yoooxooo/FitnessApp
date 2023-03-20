package app.it_academy.fitnessAppAudit.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "Audits")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "dt_create")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant creationDate;

    @Column(name = "userId")
    private UUID userId;

    @Column(name = "mail")
    private String mail;

    @Column(name = "fio")
    private String fio;

    @Column(name = "role")
    private UserRole role;

    @Column(name = "action")
    private String action;

    @Column(name = "essence")
    private EssenceType type;

    @Column(name = "essenceId")
    private UUID essenceId;

    public Audit() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public EssenceType getType() {
        return type;
    }

    public void setType(String type) {
        this.type = EssenceType.valueOf(type);
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

    public void setType(EssenceType type) {
        this.type = type;
    }

    public UUID getEssenceId() {
        return essenceId;
    }

    public void setEssenceId(UUID essenceId) {
        this.essenceId = essenceId;
    }
}
