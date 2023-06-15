package com.softvan.hospitalManagement.responseDto;

import com.softvan.hospitalManagement.entity.RoleEntity;
import com.softvan.hospitalManagement.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleResponseDto {

    private UserEntity user;
    private RoleEntity role;
}
