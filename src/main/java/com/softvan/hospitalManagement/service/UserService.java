package com.softvan.hospitalManagement.service;

import com.softvan.hospitalManagement.requestDto.UserRequestDto;
import com.softvan.hospitalManagement.responseDto.PrivilegesResponseDto;
import com.softvan.hospitalManagement.responseDto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponseDto registerNewUser(UserRequestDto dto);

    Page<UserResponseDto> getAllUsers(String searchValue, Pageable pageable);
}
