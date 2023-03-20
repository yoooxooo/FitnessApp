package app.it_academy.fitnessAppUsers.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "mail", url = "${feign.url.mail}")
public interface MailFeignClient {

    @GetMapping(path = "/mail/{mail}/{uuid}")
    public ResponseEntity<String> sendSecret(@PathVariable("mail") String mail, @PathVariable("uuid") UUID uuid);
}
