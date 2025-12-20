package dev.idinaldo.brabank.auth_service.service;

import dev.idinaldo.brabank.auth_service.dto.role.RoleRequestDTO;
import dev.idinaldo.brabank.auth_service.dto.role.RoleResponseDTO;
import dev.idinaldo.brabank.auth_service.mapper.RoleMapper;
import dev.idinaldo.brabank.auth_service.model.role.Role;
import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import dev.idinaldo.brabank.auth_service.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final Logger logger = LoggerFactory.getLogger(RoleService.class);

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public RoleResponseDTO addRole(RoleRequestDTO roleRequestDTO) {
        try {
            Role role = roleMapper.roleRequestDtoToRole(roleRequestDTO.name());
            Role savedRole = roleRepository.save(role);
            return roleMapper.roleToRoleResponseDto(savedRole);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public Role findRoleByName(RoleName name) {
        return roleRepository.findByName(RoleName.CLIENT).orElseThrow(() -> new IllegalStateException("Required role ROLE_CLIENT not found in database"));
    }
}