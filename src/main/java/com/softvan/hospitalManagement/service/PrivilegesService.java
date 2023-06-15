package com.softvan.hospitalManagement.service;

import com.softvan.hospitalManagement.requestDto.PrivilegesRequestDto;
import com.softvan.hospitalManagement.responseDto.PrivilegesResponseDto;

public interface PrivilegesService {
    PrivilegesResponseDto createPrivilege(PrivilegesRequestDto dto);
}
