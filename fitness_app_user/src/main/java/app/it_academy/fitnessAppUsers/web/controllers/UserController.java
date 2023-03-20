package app.it_academy.fitnessAppUsers.web.controllers;

import app.it_academy.fitnessAppUsers.core.dto.pageDto.PageDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.FullUserDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.RegisterByAdminUserDto;
import app.it_academy.fitnessAppUsers.core.dto.userDto.UpdateUserDto;
import app.it_academy.fitnessAppUsers.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PageDto<FullUserDto>> getAllUsers(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                           @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return ResponseEntity.ok().body(service.getAllUsers(page, size));
    }

    @GetMapping (path = "/{uuid}")
    public ResponseEntity<FullUserDto> getSingleUser(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok().body(service.getSingleUser(uuid));
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody RegisterByAdminUserDto userDto) {
        service.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping (path = "/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<String> updateUser(
            @PathVariable("uuid") UUID uuid,
            @PathVariable("dt_update") Instant localDateTime,
            @RequestBody UpdateUserDto userDto
    ) {
        service.updateUser(uuid, localDateTime, userDto);
        return ResponseEntity.ok().build();
    }

}
