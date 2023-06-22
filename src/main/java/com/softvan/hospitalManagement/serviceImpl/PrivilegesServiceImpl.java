package com.softvan.hospitalManagement.serviceImpl;

import com.softvan.hospitalManagement.entity.PrivilegesEntity;
import com.softvan.hospitalManagement.enums.ExceptionEnum;
import com.softvan.hospitalManagement.exception.CustomException;
import com.softvan.hospitalManagement.repository.PrivilegesRepository;
import com.softvan.hospitalManagement.requestDto.PrivilegesRequestDto;
import com.softvan.hospitalManagement.responseDto.PrivilegesResponseDto;
import com.softvan.hospitalManagement.service.PrivilegesService;
import com.softvan.hospitalManagement.util.Utilities;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrivilegesServiceImpl implements PrivilegesService {
    private final PrivilegesRepository privilegesRepository;
    private final ModelMapper modelMapper;
    private final Utilities utilities;


    @Override
    public PrivilegesResponseDto createPrivilege(PrivilegesRequestDto dto) {
        privilegesRepository.findByPrivilegeNameIgnoreCase(dto.getPrivilegeName()).ifPresent(x -> {
            throw new CustomException(ExceptionEnum.PRIVILEGE_ALREADY_EXISTS.getValue(), HttpStatus.CONFLICT);
        });
        PrivilegesEntity privilege = new PrivilegesEntity();
        privilege.setPrivilegeName(dto.getPrivilegeName());
        privilege.setCreatedAt(utilities.getDate());
        privilege.setUpdatedAt(utilities.getDate());
        privilege.setStatus(Boolean.TRUE);
        privilege.setCreatedBy(utilities.currentUser());
        privilege.setUpdatedBy(utilities.currentUser());

        privilegesRepository.save(privilege);
        return mapToUserResponseDto(privilege);
    }

    @Override
    public Page<PrivilegesResponseDto> getAllPrivileges(String searchValue,Pageable pageable) {
        return privilegesRepository.getAll("%" + searchValue + "%",pageable).map(this::mapToUserResponseDto);
    }

//    @Override
//    public List<PrivilegesResponseDto> getAllPrivileges() {
//        return privilegesRepository.findAll().stream().map(p->mapToUserResponseDto(p)).collect(Collectors.toList());
//    }

    private PrivilegesResponseDto mapToUserResponseDto(PrivilegesEntity privilege) {
        return Optional.ofNullable(privilege).map(e -> {
            var result = this.modelMapper.map(e, PrivilegesResponseDto.class);
            return result;
        }).orElseGet(PrivilegesResponseDto::new);
    }


}
