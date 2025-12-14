package dev.idinaldo.brabank.auth_service.controller;

import dev.idinaldo.brabank.auth_service.dto.role.RoleRequestDTO;
import dev.idinaldo.brabank.auth_service.dto.role.RoleResponseDTO;
import dev.idinaldo.brabank.auth_service.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/")
    public ResponseEntity<RoleResponseDTO> addRole(@RequestBody @Valid RoleRequestDTO roleRequestDTO) {
        return ResponseEntity.ok(roleService.addRole(roleRequestDTO));
    }
}
