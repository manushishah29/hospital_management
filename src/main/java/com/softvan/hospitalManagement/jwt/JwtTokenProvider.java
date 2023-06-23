package com.softvan.hospitalManagement.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softvan.hospitalManagement.entity.PrivilegesEntity;
import com.softvan.hospitalManagement.entity.RolePrivilegesEntity;
import com.softvan.hospitalManagement.entity.UserEntity;
import com.softvan.hospitalManagement.entity.UserRoleEntity;
import com.softvan.hospitalManagement.repository.RolePrivilegesRepository;
import com.softvan.hospitalManagement.repository.UserRepository;
import com.softvan.hospitalManagement.repository.UserRoleRepository;
import com.softvan.hospitalManagement.requestDto.RoleRequestDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String AUTHORIZATION = "Authorization";
    private static final String USER_ID = "userId";

    private static final String SYSTEM_ROLE = "role";

    private final String apiKey = "e3LHqZMEtP7pc8HTJeU7cnslny6OQhNgaHa3Jdbjd0jK4AJUBz3xoRLS6AtuWgv4g0pu2L7OIpZvMOtGhGIZLSQ0P0oiQcY1OrvM";

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RolePrivilegesRepository rolePrivilegesRepository;
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        if (bearerToken != null) {
            return bearerToken;
        }
        return bearerToken;
    }


    public boolean validateToken(String token) throws JwtException, IllegalArgumentException {
        Jwts.parser().setSigningKey(apiKey).parseClaimsJws(token);
        return true;
    }

    public Authentication getAuthentication(String token) throws JsonProcessingException {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(getSystemRole(token)));
        UserDetails userDetails = new User(getUsername(token), getUsername(token), true, false, false, false, grantedAuthorities);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(apiKey).parseClaimsJws(token).getBody().getSubject();
    }

//    public List<String> getPermissions(String token) {
//        return (List<String>) Jwts.parser().setSigningKey(apiKey).parseClaimsJws(token).getBody().get("permissions");
//    }


    public Integer getUserIdToken(String token) {
        return (Integer) Jwts.parser().setSigningKey(apiKey).parseClaimsJws(token).getBody().get(USER_ID);
    }

    public String getSystemRole(String token) {
        return (String) Jwts.parser().setSigningKey(apiKey).parseClaimsJws(token).getBody().get(SYSTEM_ROLE);
    }


    // create token for login user
    public String createToken(String username, Integer userId, String role, List<PrivilegesEntity> privilegesEntities) throws JsonProcessingException {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put(USER_ID, userId);
        claims.put(SYSTEM_ROLE, role);
        Date now = new Date();
        Date validity = new Date(now.getTime() + 900000);
        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, apiKey)//
                .compact();
    }

    // create new token from old token
    public String createNewToken(String token) throws JsonProcessingException {

        Claims claims = Jwts.claims().setSubject(getUsername(token));
        claims.put(USER_ID, getUserIdToken(token));
        claims.put(SYSTEM_ROLE, getSystemRole(token));
        Date now = new Date();
        Date validity = new Date(now.getTime() + 900000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, apiKey)
                .compact();
    }

    public List<String> getPermission(Authentication authentication, Object permission) {

        Optional<UserEntity> user = userRepository.findByUsernameIgnoreCase(authentication.getName());
        Optional<UserRoleEntity> userRoleEntity = userRoleRepository.findByUser(user.get());
        Optional<RolePrivilegesEntity> rolePrivilegeList = rolePrivilegesRepository.findById(userRoleEntity.get().getId());
        List<PrivilegesEntity> privilegeList = rolePrivilegeList.stream().map(RolePrivilegesEntity::getPrivilege).collect(Collectors.toList());
        List<String> permissions = privilegeList.stream().map(x -> x.getPrivilegeName()).collect(Collectors.toList());

        return permissions;
    }
}
