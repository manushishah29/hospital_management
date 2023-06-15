package com.softvan.hospitalManagement.service;

import com.softvan.hospitalManagement.requestDto.JwtRequestDto;
import com.softvan.hospitalManagement.responseDto.JwtResponseDto;

public interface LoginService {
    JwtResponseDto createJwtToken(JwtRequestDto dto);
}
