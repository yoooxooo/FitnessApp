package app.it_academy.fitnessAppUsers.web.controllers;

import app.it_academy.fitnessAppUsers.core.dto.userDto.FullUserDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.LoginUserDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.RegisterUserDto;
import app.it_academy.fitnessAppUsers.service.api.ILoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullFields;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class LoginController {

    private final ILoginService service;

    public LoginController(ILoginService service) {
        this.service = service;
    }

    @GetMapping(path = "/verification")
    public ResponseEntity<String> verify(@RequestParam String code, @RequestParam String mail) {
        service.verify(code, mail);
        return ResponseEntity.ok().body("Аккаунт верифицирован!\nТеперь вы можете войти с помощью своего аккаунта");
    }

    @GetMapping(path = "/me")
    public ResponseEntity<FullUserDto> getCurrentUser() {
        return ResponseEntity.ok().body(service.getCurrentUser());
    }

    @PostMapping (path = "/registration")
    public ResponseEntity<String> register(@RequestBody RegisterUserDto userDto) {
        service.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping (path = "/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginUserDto loginDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.login(loginDto));
    }
}
