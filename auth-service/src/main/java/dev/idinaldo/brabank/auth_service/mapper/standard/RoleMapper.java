package dev.idinaldo.brabank.auth_service.mapper.standard;

import dev.idinaldo.brabank.auth_service.dto.role.RoleRequestDTO;
import dev.idinaldo.brabank.auth_service.dto.role.RoleResponseDTO;
import dev.idinaldo.brabank.auth_service.model.role.Role;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Role roleRequestDtoToRole(RoleRequestDTO roleRequestDTO);
    RoleResponseDTO roleToRoleResponseDto(Role role);
}
