package com.softvan.hospitalManagement.requestDto;

import com.softvan.hospitalManagement.entity.PrivilegesEntity;
import com.softvan.hospitalManagement.entity.RoleEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RolePrivilegesRequestDto {

    private RoleEntity role;
    private PrivilegesEntity privilege;
}
