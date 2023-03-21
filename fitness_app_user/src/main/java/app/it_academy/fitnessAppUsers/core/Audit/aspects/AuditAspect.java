package app.it_academy.fitnessAppUsers.core.Audit.aspects;

import app.it_academy.fitnessAppUsers.core.Audit.annotations.Audited;
import app.it_academy.fitnessAppUsers.core.dto.auditDto.CreateAuditDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.AuditUserDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.FullUserDto;
import app.it_academy.fitnessAppUsers.domain.User;
import app.it_academy.fitnessAppUsers.feign.AuditFeignClient;
import app.it_academy.fitnessAppUsers.service.UserDetailsService;
import app.it_academy.fitnessAppUsers.service.UserHolder;
import app.it_academy.fitnessAppUsers.service.UserService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class AuditAspect {

    private final AuditFeignClient auditFeignClient;

    private final UserHolder userHolder;

    private final UserService userService;

    private final UserDetailsService system;

    public AuditAspect(UserHolder userHolder, UserService userService, UserDetailsService userDetailsService, AuditFeignClient auditFeignClient) {
        this.auditFeignClient = auditFeignClient;
        this.userHolder = userHolder;
        this.userService = userService;
        this.system = userDetailsService;
    }
    @Pointcut("@annotation(audited)")
    public void isAudited(Audited audited) {
    }

    @AfterReturning(value = "isAudited(audited))", argNames = "audited, uuid", returning = "uuid")
    public void createAudit(Audited audited, UUID uuid) {
        CreateAuditDto auditDto = new CreateAuditDto();
        auditDto.setAction(audited.operationType());
        auditDto.setType("USER");
        auditDto.setEssenceId(uuid);
        AuditUserDto auditUserDto = new AuditUserDto();
        if(userHolder.getSecurity() == "anonymousUser") {
            User system = this.system.getSystem();
            auditUserDto.setUserId(system.getId());
            auditUserDto.setMail(system.getMail());
            auditUserDto.setFio(system.getFio());
            auditUserDto.setRole(system.getRole());
        } else {
            FullUserDto actionUser = userService.getSingleUser(userHolder.getUser().getId());
            auditUserDto.setUserId(actionUser.getId());
            auditUserDto.setMail(actionUser.getMail());
            auditUserDto.setFio(actionUser.getFio());
            auditUserDto.setRole(actionUser.getRole());
        }
        auditDto.setUser(auditUserDto);
        auditFeignClient.createAudit(auditDto);
    }
}
