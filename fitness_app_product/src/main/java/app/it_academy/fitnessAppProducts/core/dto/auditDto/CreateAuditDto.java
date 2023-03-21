package app.it_academy.fitnessAppProducts.core.dto.auditDto;

import app.it_academy.fitnessAppProducts.core.dto.usersDto.AuditUserDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CreateAuditDto {

    private AuditUserDto user;

    @JsonProperty(value = "text")
    private String action;

    private String type;

    @JsonProperty(value = "id")
    private UUID essenceId;

    public CreateAuditDto() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getEssenceId() {
        return essenceId;
    }

    public void setEssenceId(UUID essenceId) {
        this.essenceId = essenceId;
    }
}
