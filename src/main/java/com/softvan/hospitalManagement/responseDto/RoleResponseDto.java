package com.softvan.hospitalManagement.responseDto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDto {

    private String roleName;
    private Set<String> privilege;
}
