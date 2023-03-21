package app.it_academy.fitnessAppProducts.core.audit.aspects;

import app.it_academy.fitnessAppProducts.core.audit.annotations.Audited;
import app.it_academy.fitnessAppProducts.core.dto.auditDto.CreateAuditDto;
import app.it_academy.fitnessAppProducts.core.dto.usersDto.AuditUserDto;
import app.it_academy.fitnessAppProducts.core.dto.usersDto.FullUserDto;
import app.it_academy.fitnessAppProducts.feign.AuditFeignClient;
import app.it_academy.fitnessAppProducts.feign.UsersFeignClient;
import app.it_academy.fitnessAppProducts.service.UserHolder;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class AuditAspect {

    private final AuditFeignClient auditFeignClient;

    private final UsersFeignClient usersFeignClient;

    private final UserHolder userHolder;

    public AuditAspect(UserHolder userHolder, AuditFeignClient auditFeignClient, UsersFeignClient usersFeignClient) {
        this.auditFeignClient = auditFeignClient;
        this.userHolder = userHolder;
        this.usersFeignClient = usersFeignClient;
    }
    @Pointcut("@annotation(audited)")
    public void isAudited(Audited audited) {
    }

    @AfterReturning(value = "isAudited(audited))", argNames = "audited, uuid", returning = "uuid")
    public void createAudit(Audited audited, UUID uuid) {
        CreateAuditDto auditDto = new CreateAuditDto();
        auditDto.setAction(audited.operationType());
        auditDto.setType(audited.essenceType());
        auditDto.setEssenceId(uuid);
        AuditUserDto auditUserDto = new AuditUserDto();

        FullUserDto actionUser = usersFeignClient.getActionUser(userHolder.getUser().getId()).getBody();
        auditUserDto.setUserId(actionUser.getId());
        auditUserDto.setMail(actionUser.getMail());
        auditUserDto.setFio(actionUser.getFio());
        auditUserDto.setRole(actionUser.getRole());

        auditDto.setUser(auditUserDto);
        auditFeignClient.createAudit(auditDto);
    }
}
