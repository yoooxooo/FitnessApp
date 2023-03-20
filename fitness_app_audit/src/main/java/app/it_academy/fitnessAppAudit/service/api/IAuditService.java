package app.it_academy.fitnessAppAudit.service.api;

import app.it_academy.fitnessAppAudit.core.dto.auditDto.AuditDto;
import app.it_academy.fitnessAppAudit.core.dto.auditDto.CreateAuditDto;
import app.it_academy.fitnessAppAudit.core.dto.pageDto.PageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface IAuditService {

    public PageDto<AuditDto> getAllAudits(Integer page, Integer size);

    public AuditDto getSingleAudit(UUID uuid);

    public void createAudit(CreateAuditDto auditDto);
}
