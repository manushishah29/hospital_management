package com.softvan.hospitalManagement.service;

import com.softvan.hospitalManagement.requestDto.RoleRequestDto;
import com.softvan.hospitalManagement.responseDto.RoleResponseDto;

public interface RoleService {
    RoleResponseDto createRole(RoleRequestDto roleRequestDto);
}
