package com.softvan.hospitalManagement.requestDto;

import com.softvan.hospitalManagement.entity.RoleEntity;
import com.softvan.hospitalManagement.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleRequestDto {

    private UserEntity user;
    private RoleEntity role;
}
