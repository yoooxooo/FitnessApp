package app.it_academy.fitnessAppAudit.core.audit.aspects;


import app.it_academy.fitnessAppAudit.core.audit.annotations.SecureCheck;
import app.it_academy.fitnessAppAudit.core.dto.usersDto.FullUserDto;
import app.it_academy.fitnessAppAudit.domain.UserRole;
import app.it_academy.fitnessAppAudit.domain.UserStatus;
import app.it_academy.fitnessAppAudit.exceptions.custom.DeniedAccessException;
import app.it_academy.fitnessAppAudit.feign.UsersFeignClient;
import app.it_academy.fitnessAppAudit.utils.UserHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    private final UsersFeignClient usersFeignClient;

    private final UserHolder userHolder;

    public SecurityAspect(UsersFeignClient usersFeignClient, UserHolder userHolder) {
        this.usersFeignClient = usersFeignClient;
        this.userHolder = userHolder;
    }

    @Pointcut("@annotation(secureCheck)")
    public void isSecured(SecureCheck secureCheck) {
    }

    @Before(value = "isSecured(secureCheck))", argNames = "secureCheck")
    public void checkJwt(SecureCheck secureCheck) {
        ResponseEntity<FullUserDto> userResponse = usersFeignClient.getActionUser(userHolder.getUser().getId());
        if (userResponse.getStatusCode().equals(HttpStatus.OK)) {
            FullUserDto user = userResponse.getBody();
            if(user.getStatus().equals(UserStatus.ACTIVATED)) {
                if (user.getRole().equals(UserRole.ADMIN) || user.getRole().equals(UserRole.valueOf(secureCheck.role()))) {
                    return;
                }
            }
        }
        throw new DeniedAccessException("Данные токена отличны от данных на базе");
    }
}
