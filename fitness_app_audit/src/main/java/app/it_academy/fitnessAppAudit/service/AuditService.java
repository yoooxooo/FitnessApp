package app.it_academy.fitnessAppAudit.service;


import app.it_academy.fitnessAppAudit.core.dto.auditDto.AuditDto;
import app.it_academy.fitnessAppAudit.core.dto.auditDto.CreateAuditDto;
import app.it_academy.fitnessAppAudit.core.dto.pageDto.PageDto;
import app.it_academy.fitnessAppAudit.dao.AuditRepository;
import app.it_academy.fitnessAppAudit.domain.Audit;
import app.it_academy.fitnessAppAudit.mappers.AuditMapper;
import app.it_academy.fitnessAppAudit.mappers.PageMapper;
import app.it_academy.fitnessAppAudit.service.api.IAuditService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuditService implements IAuditService {

    private final AuditRepository repository;

    private final AuditMapper mapper;

    private final PageMapper<AuditDto> pageMapper;

    public AuditService(AuditRepository repository, AuditMapper mapper, PageMapper<AuditDto> pageMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.pageMapper = pageMapper;
    }

    @Override
    public PageDto<AuditDto> getAllAudits(Integer pageNumber, Integer pageSize) {
        if (pageNumber < 0 || pageSize < 1) {
            throw new IllegalArgumentException("Страницы с такими параметрами не существует");
        }
        Page<Audit> auditPage;
        if ((auditPage = repository.findAll(PageRequest.of(pageNumber, pageSize))).getTotalPages() < pageNumber + 1) {
            throw new IllegalArgumentException("Общее количество страниц меньше чем номер запрашиваемой");
        }
        return pageMapper.toDto(auditPage.map(mapper::createDto));
    }

    @Override
    public AuditDto getSingleAudit(UUID uuid) {
        Audit audit = repository.findById(uuid).orElseThrow();
        return mapper.createDto(audit);
    }

    @Override
    public void createAudit(CreateAuditDto auditDto) {
        repository.save(mapper.createEntity(auditDto));
    }
}
