package dev.idinaldo.brabank.auth_service.mapper.facade;

import dev.idinaldo.brabank.auth_service.dto.role.RoleRequestDTO;
import dev.idinaldo.brabank.auth_service.dto.role.RoleResponseDTO;
import dev.idinaldo.brabank.auth_service.mapper.secure.RoleSecureMapper;
import dev.idinaldo.brabank.auth_service.mapper.standard.RoleMapper;
import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import dev.idinaldo.brabank.auth_service.model.role.Role;
import org.springframework.stereotype.Component;
import java.util.Set;


@Component
public class RoleMapperFacade {

    private final RoleMapper roleMapper;
    private final RoleSecureMapper roleSecureMapper;

    public RoleMapperFacade(RoleMapper roleMapper, RoleSecureMapper roleSecureMapper) {
        this.roleMapper = roleMapper;
        this.roleSecureMapper = roleSecureMapper;
    }

    /**
     * @param roleRequestDTO
     * @return role
     */
    public Role roleRequestDtoToRole(RoleRequestDTO roleRequestDTO) {
        return roleMapper.roleRequestDtoToRole(roleRequestDTO);
    }

    /**
     * @param role
     * @return roleResponseDTO
     */
    public RoleResponseDTO roleToRoleResponseDto(Role role) {
        return roleMapper.roleToRoleResponseDto(role);
    }

    /**
     * @param roles
     * @return roleNames
     */
    public Set<RoleName> rolesToRoleNames(Set<Role> roles) {
        return roleSecureMapper.rolesToRoleNames(roles);
    }

    /**
     * @param roleNames
     * @return roles
     */
    public Set<Role> roleNamesToRoles(Set<RoleName> roleNames) {
        return roleSecureMapper.roleNamesToRoles(roleNames);
    }
}
