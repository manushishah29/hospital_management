package com.softvan.hospitalManagement.controller;

import com.softvan.hospitalManagement.enums.ApiResponsesEnum;
import com.softvan.hospitalManagement.requestDto.PrivilegesRequestDto;
import com.softvan.hospitalManagement.responseDto.ApiResponse;
import com.softvan.hospitalManagement.responseDto.PrivilegesResponseDto;
import com.softvan.hospitalManagement.service.PrivilegesService;
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
import java.util.List;

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

    @PreAuthorize("hasAnyRole('ROLE_SUPER')")
    @GetMapping("/getAllPrivilege")
    public ResponseEntity<ApiResponse> getAllPrivilege(@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                       @RequestParam(value = "searchValue", required = false, defaultValue = "") String searchValue,
                                                       @RequestParam(value = "sortBy", required = false, defaultValue = "privilegeName") String sortBy,
                                                       @RequestParam(value = "sortAs", required = false, defaultValue = "ASC") Sort.Direction sortAs ){

        Pageable pageable= PageRequest.of(pageNo,pageSize,Sort.by(sortAs,sortBy));

        Page<PrivilegesResponseDto> responseDto = privilegesService.getAllPrivileges(searchValue,pageable);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,ApiResponsesEnum.PRIVILEGES_FETCHED_SUCCESSFULLY.getValue(),responseDto),HttpStatus.OK);
    }

}
