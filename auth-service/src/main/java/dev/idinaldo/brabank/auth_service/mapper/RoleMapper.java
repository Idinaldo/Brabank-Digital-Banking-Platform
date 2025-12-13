package dev.idinaldo.brabank.auth_service.mapper;

import dev.idinaldo.brabank.auth_service.dto.role.RoleResponseDTO;
import dev.idinaldo.brabank.auth_service.model.role.Role;
import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role roleRequestDtoToRole(RoleName name);
    RoleResponseDTO roleToRoleResponseDto(Role role);
}
