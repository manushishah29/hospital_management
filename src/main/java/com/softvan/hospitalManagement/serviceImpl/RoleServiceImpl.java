package com.softvan.hospitalManagement.serviceImpl;

import com.softvan.hospitalManagement.entity.PrivilegesEntity;
import com.softvan.hospitalManagement.entity.RoleEntity;
import com.softvan.hospitalManagement.entity.RolePrivilegesEntity;
import com.softvan.hospitalManagement.enums.ExceptionEnum;
import com.softvan.hospitalManagement.exception.CustomException;
import com.softvan.hospitalManagement.repository.PrivilegesRepository;
import com.softvan.hospitalManagement.repository.RolePrivilegesRepository;
import com.softvan.hospitalManagement.repository.RoleRepository;
import com.softvan.hospitalManagement.requestDto.RoleRequestDto;
import com.softvan.hospitalManagement.responseDto.RoleResponseDto;
import com.softvan.hospitalManagement.service.RoleService;
import com.softvan.hospitalManagement.util.Utilities;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final RolePrivilegesRepository rolePrivilegesRepository;

    private final ModelMapper modelMapper;

    private final PrivilegesRepository privilegeRepository;
    private final Utilities utilities;

    @Override
    public RoleResponseDto createRole(RoleRequestDto dto) {

        roleRepository.findByRoleNameIgnoreCase(dto.getRoleName()).ifPresent(x -> {
            throw new CustomException(ExceptionEnum.ROLE_ALREADY_EXISTS.getValue(), HttpStatus.CONFLICT);
        });

        RoleEntity role = new RoleEntity();
        role.setRoleName(dto.getRoleName());
        role.setCreatedBy(utilities.currentUser());
        role.setUpdatedBy(utilities.currentUser());
        role.setStatus(Boolean.TRUE);

        roleRepository.save(role);

        Set<PrivilegesEntity> privileges = new HashSet<>();
        dto.getPrivilege().forEach(privilage -> {
            PrivilegesEntity byPrivilegeName = privilegeRepository.findByPrivilegeNameIgnoreCase(privilage).orElse(new PrivilegesEntity());
            if (byPrivilegeName.getPrivilegeName() == null || byPrivilegeName.getPrivilegeName().isEmpty()) {
                PrivilegesEntity privilege1 = new PrivilegesEntity();
                privilege1.setPrivilegeName(privilage);
                privilege1.setCreatedBy(utilities.currentUser());
                privilege1.setUpdatedBy(utilities.currentUser());
                privilege1.setStatus(Boolean.TRUE);
                PrivilegesEntity save = this.privilegeRepository.save(privilege1);
                privileges.add(save);
            } else {
                privileges.add(byPrivilegeName);
            }
        });
        privileges.forEach(privilege -> {
            RolePrivilegesEntity privilegeRole = new RolePrivilegesEntity();
            privilegeRole.setRole(role);
            privilegeRole.setPrivilege(privilege);


            rolePrivilegesRepository.save(privilegeRole);
        });
        return mapToUserResponseDto(role, privileges);
    }

    private RoleResponseDto mapToUserResponseDto(RoleEntity role, Set<PrivilegesEntity> privilege) {
        return Optional.ofNullable(role).map(e -> {
            var result = this.modelMapper.map(e, RoleResponseDto.class);
            result.setPrivilege(privilege.stream().map(p -> {
                return p.getPrivilegeName();
            }).collect(Collectors.toSet()));
            return result;
        }).orElseGet(RoleResponseDto::new);
    }
}
