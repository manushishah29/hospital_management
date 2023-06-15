package com.softvan.hospitalManagement.controller;

import com.softvan.hospitalManagement.enums.ApiResponsesEnum;
import com.softvan.hospitalManagement.requestDto.PrivilegesRequestDto;
import com.softvan.hospitalManagement.responseDto.ApiResponse;
import com.softvan.hospitalManagement.responseDto.PrivilegesResponseDto;
import com.softvan.hospitalManagement.service.PrivilegesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/privileges")
public class PrivilegesController {

    private final PrivilegesService privilegesService;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPER')")
    @PostMapping("/createPrivilege")
    public ResponseEntity<ApiResponse> createPrivilege(@RequestBody @Valid PrivilegesRequestDto dto) {
        PrivilegesResponseDto responseDto = privilegesService.createPrivilege(dto);
        System.out.println("responseDto = " + responseDto);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, ApiResponsesEnum.PRIVILEGE_CREATED_SUCCESSFULLY.getValue(), responseDto), HttpStatus.OK);
    }
}
