package com.softvan.hospitalManagement.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softvan.hospitalManagement.entity.PrivilegesEntity;
import com.softvan.hospitalManagement.entity.RolePrivilegesEntity;
import com.softvan.hospitalManagement.entity.UserEntity;
import com.softvan.hospitalManagement.entity.UserRoleEntity;
import com.softvan.hospitalManagement.enums.ExceptionEnum;
import com.softvan.hospitalManagement.exception.CustomException;
import com.softvan.hospitalManagement.jwt.JwtTokenProvider;
import com.softvan.hospitalManagement.repository.RolePrivilegesRepository;
import com.softvan.hospitalManagement.repository.UserRepository;
import com.softvan.hospitalManagement.repository.UserRoleRepository;
import com.softvan.hospitalManagement.requestDto.JwtRequestDto;
import com.softvan.hospitalManagement.responseDto.JwtResponseDto;
import com.softvan.hospitalManagement.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRoleRepository userRoleRepository;

    private final RolePrivilegesRepository rolePrivilegesRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponseDto createJwtToken(JwtRequestDto getTokenRequestDto) {

        Optional<UserEntity> userEntity = userRepository.findByEmailIgnoreCase(getTokenRequestDto.getEmail());

        var user=userRepository.findByEmailIgnoreCase(getTokenRequestDto.getEmail())
                .orElseThrow(() -> new CustomException(ExceptionEnum.USERNAME_NOT_FOUND.getValue(), HttpStatus.BAD_REQUEST));

        if(!passwordEncoder.matches(getTokenRequestDto.getPassword(),user.getPassword())){
            throw new CustomException(ExceptionEnum.INCORRECT_PASSWORD.getValue(), HttpStatus.NOT_FOUND);
        }
        return getTokenResponse(userEntity.get());

    }

    private JwtResponseDto getTokenResponse(UserEntity userEntity) {

        String role = null;
        Optional<UserRoleEntity> systemUserRoleMappingEntity = userRoleRepository.findByUser(userEntity);

        role = systemUserRoleMappingEntity.get().getRole().getRoleName();

        System.out.println("systemUserRoleMappingEntity systemRole ::::::::::= " + role);
        System.out.println("systemUserRoleMappingEntity :::::: = " + systemUserRoleMappingEntity.get());

        Optional<RolePrivilegesEntity> roleRightsMappingEntity = rolePrivilegesRepository.findById(systemUserRoleMappingEntity.get().getId());



        System.out.println("roleRightsMappingEntity :::::= " + roleRightsMappingEntity.toString());
        List<PrivilegesEntity> rightsEntitiesList = roleRightsMappingEntity.stream().map(RolePrivilegesEntity::getPrivilege).collect(Collectors.toList());

        try {
            return new JwtResponseDto(jwtTokenProvider.createToken(userEntity.getEmail(), userEntity.getId(),role, rightsEntitiesList));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
