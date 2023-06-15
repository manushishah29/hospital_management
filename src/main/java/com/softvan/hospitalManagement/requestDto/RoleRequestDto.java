package com.softvan.hospitalManagement.requestDto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDto {

    @NotEmpty(message = "role name must not be empty")
    private String roleName;

    @NotEmpty(message = "privileges must not be empty")
    private Set<String> privilege;
}
