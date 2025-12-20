package dev.idinaldo.brabank.auth_service.mapper;

import dev.idinaldo.brabank.auth_service.dto.role.RoleResponseDTO;
import dev.idinaldo.brabank.auth_service.model.role.Role;
import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Role roleRequestDtoToRole(RoleName name);
    RoleResponseDTO roleToRoleResponseDto(Role role);
}
