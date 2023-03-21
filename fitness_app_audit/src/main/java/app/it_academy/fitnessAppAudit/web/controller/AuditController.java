package app.it_academy.fitnessAppAudit.web.controller;


import app.it_academy.fitnessAppAudit.core.audit.annotations.SecureCheck;
import app.it_academy.fitnessAppAudit.core.dto.auditDto.AuditDto;
import app.it_academy.fitnessAppAudit.core.dto.auditDto.CreateAuditDto;
import app.it_academy.fitnessAppAudit.core.dto.pageDto.PageDto;
import app.it_academy.fitnessAppAudit.service.api.IAuditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/audit")
public class AuditController {

    private final IAuditService service;


    public AuditController(IAuditService service) {
        this.service = service;
    }

    @GetMapping
    @SecureCheck(role = "ADMIN")
    public ResponseEntity<PageDto<AuditDto>> getAllAudits(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                          @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return ResponseEntity.ok().body(service.getAllAudits(page, size));
    }

    @GetMapping (path = "/{uuid}")
    @SecureCheck(role = "ADMIN")
    public ResponseEntity<AuditDto> getSingleAudit(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok().body(service.getSingleAudit(uuid));
    }

    @PostMapping (path = "/sys")
    public ResponseEntity<String> createAudit(@RequestBody CreateAuditDto auditDto) {
        service.createAudit(auditDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
