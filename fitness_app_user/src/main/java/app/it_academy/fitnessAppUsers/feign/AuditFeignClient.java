package app.it_academy.fitnessAppUsers.feign;

import app.it_academy.fitnessAppUsers.core.dto.auditDto.CreateAuditDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "audit", url = "${feign.url.audit}")
public interface AuditFeignClient {

    @PostMapping(path = "/audit")
    public ResponseEntity<String> createAudit(@RequestBody CreateAuditDto auditDto);
}
