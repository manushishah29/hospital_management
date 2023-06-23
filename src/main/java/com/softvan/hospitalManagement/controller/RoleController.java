package com.softvan.hospitalManagement.controller;

import com.softvan.hospitalManagement.enums.ApiResponsesEnum;
import com.softvan.hospitalManagement.requestDto.RoleRequestDto;
import com.softvan.hospitalManagement.responseDto.ApiResponse;
import com.softvan.hospitalManagement.responseDto.RoleResponseDto;
import com.softvan.hospitalManagement.responseDto.UserResponseDto;
import com.softvan.hospitalManagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getRole")
    public ResponseEntity<ApiResponse> getRole(@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                               @RequestParam(value = "searchValue", required = false, defaultValue = "") String searchValue,
                                               @RequestParam(value = "sortBy", required = false, defaultValue = "Id") String sortBy,
                                               @RequestParam(value = "sortAs", required = false, defaultValue = "ASC") Sort.Direction sortAs ){
        Pageable pageable= PageRequest.of(pageNo,pageSize,Sort.by(sortAs,sortBy));
        Page<RoleResponseDto> responseDto = roleService.getRole(searchValue,pageable);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,ApiResponsesEnum.ROLE_FETCHED_SUCCESSFULLY.getValue(),responseDto),HttpStatus.OK);
    }



}
