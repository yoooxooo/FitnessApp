package app.it_academy.fitnessAppAudit.core.dto.auditDto;

import app.it_academy.fitnessAppAudit.core.dto.usersDto.AuditUserDto;
import app.it_academy.fitnessAppAudit.domain.EssenceType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.UUID;

public class AuditDto {

    @JsonProperty(value = "uuid")
    private UUID id;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    @JsonProperty(value = "dt_create")
    private Instant creationDate;

    @JsonProperty(value = "user")
    private AuditUserDto user;

    @JsonProperty(value = "text")
    private String action;

    @JsonProperty(value = "type")
    private EssenceType type;

    @JsonProperty(value = "id")
    private UUID essenceId;


    public AuditDto() {
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

    public AuditUserDto getUser() {
        return user;
    }

    public void setUser(AuditUserDto user) {
        this.user = user;
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
