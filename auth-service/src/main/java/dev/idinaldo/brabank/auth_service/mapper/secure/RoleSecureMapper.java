package dev.idinaldo.brabank.auth_service.mapper.secure;


import dev.idinaldo.brabank.auth_service.model.role.Role;
import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import dev.idinaldo.brabank.auth_service.service.RoleService;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
public class RoleSecureMapper {

    private final RoleService roleService;

    public RoleSecureMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * @param roles
     * @return roleNames
     */
    public Set<RoleName> rolesToRoleNames(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }

    /**
     * @param roleNames
     * @return roles
     */
    public Set<Role> roleNamesToRoles(Set<RoleName> roleNames) {
        return roleNames.stream().map(roleService::findRoleByName).collect(Collectors.toSet());
    }

    public Set<RoleName> getRoleNameSet(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }
}
