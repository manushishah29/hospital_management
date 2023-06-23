package com.softvan.hospitalManagement.service;

import com.softvan.hospitalManagement.requestDto.RoleRequestDto;
import com.softvan.hospitalManagement.responseDto.RoleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    RoleResponseDto createRole(RoleRequestDto roleRequestDto);

    Page<RoleResponseDto> getRole(String searchValue, Pageable pageable);
}
