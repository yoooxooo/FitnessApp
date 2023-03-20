package app.it_academy.fitnessAppAudit.mappers;


import app.it_academy.fitnessAppAudit.core.dto.auditDto.AuditDto;
import app.it_academy.fitnessAppAudit.core.dto.auditDto.CreateAuditDto;
import app.it_academy.fitnessAppAudit.domain.Audit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AuditMapper {

    @Mapping(target = "user", expression = """
            java(new app.it_academy.fitnessAppAudit.core.dto.usersDto.AuditUserDto(
            audit.getUserId(), audit.getMail(), audit.getFio(), audit.getRole()
            ))
            """)
    @Mapping(target = "essenceId", source = "audit.essenceId")
    AuditDto createDto(Audit audit);


    @Mapping(target = "creationDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "userId", expression = "java(dto.getUser().getUserId())")
    @Mapping(target = "mail", expression = "java(dto.getUser().getMail())")
    @Mapping(target = "fio", expression = "java(dto.getUser().getFio())")
    @Mapping(target = "role", expression = "java(dto.getUser().getRole())")
    @Mapping(target = "action", source = "dto.action")
    @Mapping(target = "type", source = "dto.type")
    @Mapping(target = "essenceId", source = "dto.essenceId")
    Audit createEntity(CreateAuditDto dto);
}
