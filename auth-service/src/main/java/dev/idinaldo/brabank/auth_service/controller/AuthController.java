package dev.idinaldo.brabank.auth_service.controller;

import dev.idinaldo.brabank.auth_service.dto.user.UserRequestDTO;
import dev.idinaldo.brabank.auth_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> signup(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.register(userRequestDTO));
    }
}
