package com.softvan.hospitalManagement.service;

import com.softvan.hospitalManagement.requestDto.PrivilegesRequestDto;
import com.softvan.hospitalManagement.responseDto.PrivilegesResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrivilegesService {
    PrivilegesResponseDto createPrivilege(PrivilegesRequestDto dto);

    Page<PrivilegesResponseDto> getAllPrivileges(String searchValue,Pageable pageable);
}
