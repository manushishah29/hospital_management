package com.softvan.hospitalManagement.controller;

import com.softvan.hospitalManagement.enums.ApiResponsesEnum;
import com.softvan.hospitalManagement.requestDto.JwtRequestDto;
import com.softvan.hospitalManagement.responseDto.ApiResponse;
import com.softvan.hospitalManagement.responseDto.JwtResponseDto;
import com.softvan.hospitalManagement.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class LoginController {


    private final LoginService jwtService;

    @PostMapping({"/login"})
    public ResponseEntity<ApiResponse> createJwtToken(@RequestBody @Valid JwtRequestDto dto) throws Exception {
        JwtResponseDto jwtResponseDto = jwtService.createJwtToken(dto);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, ApiResponsesEnum.SIGN_IN_SUCCESSFULLY.getValue(), jwtResponseDto), HttpStatus.OK);
    }

}