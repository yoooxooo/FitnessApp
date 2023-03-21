package app.it_academy.fitnessAppAudit.feign;

import app.it_academy.fitnessAppAudit.core.dto.usersDto.FullUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "users", url = "${feign.url.users}")
public interface UsersFeignClient {

    @GetMapping(path = "/users/sys/get_user")
    public ResponseEntity<FullUserDto> getActionUser(@RequestBody UUID uuid);
}
