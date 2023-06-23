package com.softvan.hospitalManagement.responseDto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String username;
    private String firstName;
    private String lastName;
    private String role;
}
