package com.softvan.hospitalManagement.controller;

import com.softvan.hospitalManagement.enums.ApiResponsesEnum;
import com.softvan.hospitalManagement.requestDto.UserRequestDto;
import com.softvan.hospitalManagement.responseDto.ApiResponse;
import com.softvan.hospitalManagement.responseDto.PrivilegesResponseDto;
import com.softvan.hospitalManagement.responseDto.UserResponseDto;
import com.softvan.hospitalManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerNewUser(@RequestBody @Valid UserRequestDto dto) {
        userService.registerNewUser(dto);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, ApiResponsesEnum.USER_CREATED_SUCCESSFULLY.getValue(), dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER')")
    @GetMapping("/getAllUser")
    public ResponseEntity<ApiResponse> getAllPrivilege(@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                       @RequestParam(value = "searchValue", required = false, defaultValue = "") String searchValue,
                                                       @RequestParam(value = "sortBy", required = false, defaultValue = "firstName") String sortBy,
                                                       @RequestParam(value = "sortAs", required = false, defaultValue = "ASC") Sort.Direction sortAs ){

        Pageable pageable= PageRequest.of(pageNo,pageSize,Sort.by(sortAs,sortBy));
        Page<UserResponseDto> responseDto = userService.getAllUsers(searchValue,pageable);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,ApiResponsesEnum.PRIVILEGES_FETCHED_SUCCESSFULLY.getValue(),responseDto),HttpStatus.OK);
    }


}
