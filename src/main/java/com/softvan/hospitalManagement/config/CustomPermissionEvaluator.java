package com.softvan.hospitalManagement.config;

import com.softvan.hospitalManagement.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        System.out.println("targetDomainObject = " + targetDomainObject); //  target
        System.out.println("permission = " + permission);  // privileges
        System.out.println("authentication.getName() ::::::::::= " + authentication.getName());
        List<String> permissions = jwtTokenProvider.getPermission(authentication, permission);
        System.out.println("permissions.contains(permission.toString()) = " + permissions.contains(permission.toString()));
        return permissions.contains(permission.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return true;
    }
}
