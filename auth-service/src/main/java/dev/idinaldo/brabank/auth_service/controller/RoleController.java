package dev.idinaldo.brabank.auth_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @PostMapping("/")
    public String addRole() {
        return "POST /api/roles/";
    }
}
