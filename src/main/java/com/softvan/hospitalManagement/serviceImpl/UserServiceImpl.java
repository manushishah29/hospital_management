package com.softvan.hospitalManagement.serviceImpl;

import com.softvan.hospitalManagement.entity.RoleEntity;
import com.softvan.hospitalManagement.entity.UserEntity;
import com.softvan.hospitalManagement.entity.UserRoleEntity;
import com.softvan.hospitalManagement.enums.ExceptionEnum;
import com.softvan.hospitalManagement.exception.CustomException;
import com.softvan.hospitalManagement.repository.RoleRepository;
import com.softvan.hospitalManagement.repository.UserRepository;
import com.softvan.hospitalManagement.repository.UserRoleRepository;
import com.softvan.hospitalManagement.requestDto.UserRequestDto;
import com.softvan.hospitalManagement.responseDto.PrivilegesResponseDto;
import com.softvan.hospitalManagement.responseDto.RoleResponseDto;
import com.softvan.hospitalManagement.responseDto.UserResponseDto;
import com.softvan.hospitalManagement.service.UserService;
import com.softvan.hospitalManagement.util.Utilities;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final Utilities utilities;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto registerNewUser(UserRequestDto dto) {


        var userEntity1 = userRepository.findByUsernameIgnoreCase(dto.getUsername());
        if (userEntity1.isPresent()) {
            throw new CustomException(ExceptionEnum.USER_ALREADY_EXISTS.getValue(), HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setCreatedAt(utilities.getDate());
        userEntity.setUpdatedAt(utilities.getDate());
        userEntity.setStatus(Boolean.TRUE);


        //save user
        userEntity.setCreatedBy(userEntity);
        userEntity.setUpdatedBy(userEntity);
        userRepository.save(userEntity);

        //UserRole Mapping
        RoleEntity role = roleRepository.findByRoleNameIgnoreCase(dto.getRole()).
                orElseThrow(() -> new CustomException(ExceptionEnum.ROLE_NOT_FOUND.getValue(), HttpStatus.NOT_FOUND));

        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setRole(role);
        userRole.setUser(userEntity);
        userRole.setCreatedBy(utilities.currentUser());
        userRole.setUpdatedBy(utilities.currentUser());
        userRole.setStatus(Boolean.TRUE);
        userRoleRepository.save(userRole);

        //update userEntity
        return mapToUserResponseDto(userEntity);

    }

    @Override
    public Page<UserResponseDto> getAllUsers(String searchValue, Pageable pageable) {
        return userRepository.getAll("%"+searchValue+"%",pageable).map(this::mapToUserResponseDto);
    }

    private UserResponseDto mapToUserResponseDto(UserEntity user) {
        return Optional.ofNullable(user).map(e -> {
            var userRoleMapping=userRoleRepository.findByUser(user);
            var result = this.modelMapper.map(e, UserResponseDto.class);
            Optional.ofNullable(userRoleMapping).ifPresent(u->result.setRole(u.get().getRole().getRoleName()));
             return result;
        }).orElseGet(UserResponseDto::new);
    }





}
