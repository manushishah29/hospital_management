package com.softvan.hospitalManagement.controller;

import com.softvan.hospitalManagement.enums.ApiResponsesEnum;
import com.softvan.hospitalManagement.requestDto.RoleRequestDto;
import com.softvan.hospitalManagement.responseDto.ApiResponse;
import com.softvan.hospitalManagement.responseDto.RoleResponseDto;
import com.softvan.hospitalManagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;


    @PostMapping("/createRole")
    public ResponseEntity<ApiResponse> createRole(@RequestBody @Valid RoleRequestDto roleRequestDto) {
        RoleResponseDto responseDto = roleService.createRole(roleRequestDto);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, ApiResponsesEnum.ROLE_CREATED_SUCCESSFULLY.getValue(), responseDto), HttpStatus.OK);
    }
}
