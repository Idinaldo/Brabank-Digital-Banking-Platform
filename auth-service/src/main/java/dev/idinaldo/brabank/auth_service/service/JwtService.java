package dev.idinaldo.brabank.auth_service.service;

import dev.idinaldo.brabank.auth_service.mapper.facade.RoleMapperFacade;
import dev.idinaldo.brabank.auth_service.mapper.standard.UserMapper;
import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import dev.idinaldo.brabank.auth_service.model.user.User;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {

    private final RoleMapperFacade roleMapperFacade;

    public JwtService(RoleMapperFacade roleMapperFacade) {
        this.roleMapperFacade = roleMapperFacade;
    }

    // TODO: add signWith
    public String generateToken(User user) {

        Instant now = Instant.now();
        Set<RoleName> roles = roleMapperFacade.rolesToRoleNames(user.getRoles());
        String userSubjectStr = String.valueOf(user.getUserSubject());
        String jwtToken = Jwts.builder()
                .header()
                .and()
                .issuer("Brabank Auth Service")
                .subject(userSubjectStr)
                .claim("roles", roles)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(15, ChronoUnit.MINUTES)))
                .compact();

        return jwtToken;
    }


}
