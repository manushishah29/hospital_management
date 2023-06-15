package com.softvan.hospitalManagement.responseDto;

import com.softvan.hospitalManagement.entity.PrivilegesEntity;
import com.softvan.hospitalManagement.entity.RoleEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RolePrivilegesResponseDto {

    private RoleEntity role;
    private PrivilegesEntity privilege;
}
