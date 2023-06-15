package com.softvan.hospitalManagement.controller;

import com.softvan.hospitalManagement.enums.ApiResponsesEnum;
import com.softvan.hospitalManagement.requestDto.UserRequestDto;
import com.softvan.hospitalManagement.responseDto.ApiResponse;
import com.softvan.hospitalManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
