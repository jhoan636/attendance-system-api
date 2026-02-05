package com.aulataller.attendance.controller;

import com.aulataller.attendance.dto.user.CreateUserRequest;
import com.aulataller.attendance.dto.user.UserResponse;
import com.aulataller.attendance.exception.user.UserNotFoundException;
import com.aulataller.attendance.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @GetMapping("/find/{nationalId}")
    public ResponseEntity<UserResponse> findUserByNationalId(@PathVariable String nationalId) {
        log.info("Verificando existencia del usuario con código: {}", nationalId);
        return userService.findByNationalId(nationalId)
                .map(user -> ResponseEntity.ok(
                        UserResponse.from(user)))
                .orElseThrow(() ->
                        new UserNotFoundException(nationalId));
    }

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody CreateUserRequest dto) {
        log.info("Registrando nuevo usuario con código: {}", dto.getNationalId());
        UserResponse response = userService.createUser(dto);
        log.info("Usuario registrado exitosamente: {}", dto.getNationalId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}