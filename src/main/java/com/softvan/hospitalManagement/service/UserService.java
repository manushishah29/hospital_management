package com.softvan.hospitalManagement.service;

import com.softvan.hospitalManagement.requestDto.UserRequestDto;
import com.softvan.hospitalManagement.responseDto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponseDto registerNewUser(UserRequestDto dto);
}
